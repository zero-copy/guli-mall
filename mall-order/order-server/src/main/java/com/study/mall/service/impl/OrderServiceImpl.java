package com.study.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.constant.OrderConstant;
import com.study.mall.common.dto.MemberEntityDto;
import com.study.mall.common.exception.MallException;
import com.study.mall.common.lang.R;
import com.study.mall.common.lang.dto.SkuStockDto;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.dto.*;
import com.study.mall.entity.OrderEntity;
import com.study.mall.entity.OrderItemEntity;
import com.study.mall.enume.OrderStatusEnum;
import com.study.mall.feign.*;
import com.study.mall.feign.dto.FareDto;
import com.study.mall.feign.dto.OrderItemDto;
import com.study.mall.feign.dto.WareSkuLockDto;
import com.study.mall.interceptor.LoginInterceptor;
import com.study.mall.mapper.OrderMapper;
import com.study.mall.service.IOrderItemService;
import com.study.mall.service.IOrderService;
import com.study.mall.vo.OrderConfirmVo;
import com.study.mall.vo.OrderSubmitRespVo;
import com.study.mall.vo.OrderSubmitVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 订单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@Service("orderService")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

    private ThreadLocal<OrderSubmitVo> submitThreadLocal = new ThreadLocal<>();

    @Resource
    private IMemberAddressFeignService addressFeignService;

    @Resource
    private ICartFeignService cartFeignService;

    @Resource
    private IWareSkuFeignService wareSkuFeignService;

    @Resource
    private IWareInfoFeignService wareInfoFeignService;

    @Resource
    private ThreadPoolExecutor threadPool;
    
    @Resource
    private ISpuInfoFeignService spuInfoFeignService;

    @Resource
    private IOrderItemService orderItemService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        MemberEntityDto memberDto = LoginInterceptor.THREAD_LOCAL.get();
        Long memberId = memberDto.getId();
        Integer integration = memberDto.getIntegration();
        confirmVo.setIntegration(integration);
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        CompletableFuture<Void> addressTask = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(attributes);
            R<List<MemberAddressDto>> addressRes = addressFeignService.getAddress(memberId);
            if (addressRes.getCode() == 0) {
                confirmVo.setAddress(addressRes.getData());
            }
        }, threadPool);
        CompletableFuture<Void> cartItemsTask = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(attributes);
            R<List<CartItemEntityDto>> cartItemsRes = cartFeignService.getCurrentUserCartItems();
            if (cartItemsRes.getCode() == 0) {
                confirmVo.setItems(cartItemsRes.getData());
            }
        }, threadPool).thenRunAsync(() -> {
            List<CartItemEntityDto> items = confirmVo.getItems();
            List<Long> skuIds = items.stream().map(CartItemEntityDto::getSkuId).collect(Collectors.toList());
            R<List<SkuStockDto>> stockRes = wareSkuFeignService.hasStock(skuIds);
            if (stockRes.getCode() == 0) {
                List<SkuStockDto> data = stockRes.getData();
                if (Objects.nonNull(data)) {
                    Map<Long, Boolean> stocks = data.stream().collect(Collectors.toMap(SkuStockDto::getSkuId, SkuStockDto::getHasStock));
                    confirmVo.setStocks(stocks);
                }
            }
        }, threadPool);
        CompletableFuture.allOf(addressTask, cartItemsTask).get();
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(OrderConstant.USER_ORDER_TOKEN_PREFIX + memberId, token, 30, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);
        return confirmVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderSubmitRespVo submitOrder(OrderSubmitVo submit) {
        submitThreadLocal.set(submit);
        OrderSubmitRespVo resp = new OrderSubmitRespVo();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        MemberEntityDto memberDto = LoginInterceptor.THREAD_LOCAL.get();
        String orderToken = submit.getOrderToken();
        Long result = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
                Collections.singletonList(OrderConstant.USER_ORDER_TOKEN_PREFIX + memberDto.getId()),
                orderToken);
        if (1L == result) {
            //成功
            OrderCreateDto createDto = createOrder();
            BigDecimal payAmount = createDto.getOrder().getPayAmount();
            BigDecimal payPrice = submit.getPayPrice();
            double abs = Math.abs(payAmount.subtract(payPrice).doubleValue());
            if (abs < 0.01) {
                saveOrder(createDto);
                WareSkuLockDto lockDto = new WareSkuLockDto();
                lockDto.setOrderSn(createDto.getOrder().getOrderSn());
                lockDto.setLocks(createDto.getOrderItems().stream()
                        .map(item -> {
                            OrderItemDto itemDto = BeanUtil.copyProperties(item, OrderItemDto.class);
                            itemDto.setCount(item.getSkuQuantity());
                            return itemDto;
                        })
                        .collect(Collectors.toList()));
                lockDto.setOrderId(createDto.getOrder().getId());
                R<Object> lockRes = wareSkuFeignService.orderLockStock(lockDto);
                if (lockRes.getCode() == 0) {
                    resp.setOrder(createDto.getOrder());
                    resp.setCode(0);
                    rabbitTemplate.convertAndSend("order-event-exchange",
                            "order.create.order",
                            createDto.getOrder());
                    return resp;
                } else {
                    throw new MallException("提交订单失败");
                }
            } else {
                throw new MallException("提交订单失败");
            }
        } else {
            throw new MallException("提交订单失败");
        }
    }

    @Override
    public OrderEntity getByOrderSn(String orderSn) {
        return baseMapper.selectOne(new QueryWrapper<OrderEntity>().eq(OrderEntity.ORDER_SN, orderSn));
    }

    @Override
    public void closeOrder(OrderEntity order) {
        OrderEntity dbOrder = baseMapper.selectById(order.getId());
        if (dbOrder.getStatus().equals(OrderStatusEnum.CREATE_NEW.getCode())) {
            dbOrder.setStatus(OrderStatusEnum.CANCLED.getCode());
            baseMapper.updateById(dbOrder);
            rabbitTemplate.convertAndSend("order-event-exchange", "order.release.other", dbOrder);
        }
    }

    private void saveOrder(OrderCreateDto createDto) {
        OrderEntity orderEntity = createDto.getOrder();
        orderEntity.setModifyTime(LocalDateTime.now());
        baseMapper.insert(orderEntity);
        List<OrderItemEntity> orderItems = createDto.getOrderItems();
        orderItems.forEach(items -> items.setOrderId(orderEntity.getId()));
        orderItemService.saveBatch(orderItems);
    }

    private OrderCreateDto createOrder() {
        OrderCreateDto createDto = new OrderCreateDto();
        //生成订单号
        String orderSn = IdWorker.getTimeId();
        OrderEntity orderEntity = buildOrder(orderSn);
        List<OrderItemEntity> orderItemEntities = buildOrderItems(orderSn);
        computePrice(orderEntity, orderItemEntities);
        createDto.setOrder(orderEntity);
        createDto.setOrderItems(orderItemEntities);
        createDto.setFare(orderEntity.getFreightAmount());
        createDto.setPayPrice(orderEntity.getPayAmount());
        return createDto;
    }

    private void computePrice(OrderEntity orderEntity, List<OrderItemEntity> orderItemEntities) {
        BigDecimal total = new BigDecimal(0);
        BigDecimal couponAmount = new BigDecimal(0);
        BigDecimal integrationAmount = new BigDecimal(0);
        BigDecimal promotionAmount = new BigDecimal(0);
        Integer giftGrowth = 0;
        Integer giftIntegration = 0;
        for (OrderItemEntity orderItem : orderItemEntities) {
            total = total.add(orderItem.getRealAmount());
            couponAmount = couponAmount.add(orderItem.getCouponAmount());
            integrationAmount =  integrationAmount.add(orderItem.getIntegrationAmount());
            promotionAmount = promotionAmount.add(orderItem.getPromotionAmount());
            giftGrowth += orderItem.getGiftGrowth();
            giftIntegration += orderItem.getGiftIntegration();
        }
        orderEntity.setTotalAmount(total);
        orderEntity.setPayAmount(total.add(orderEntity.getFreightAmount()));
        orderEntity.setPromotionAmount(promotionAmount);
        orderEntity.setCouponAmount(couponAmount);
        orderEntity.setIntegrationAmount(integrationAmount);
        orderEntity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        orderEntity.setAutoConfirmDay(7);
        orderEntity.setGrowth(giftGrowth);
        orderEntity.setIntegration(giftIntegration);
        orderEntity.setDeleteStatus(0);
    }

    private OrderEntity buildOrder(String orderSn) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setMemberId(LoginInterceptor.THREAD_LOCAL.get().getId());
        orderEntity.setOrderSn(orderSn);
        R<FareDto> fareRes = wareInfoFeignService.getFare(submitThreadLocal.get().getAddrId());
        if (fareRes.getCode() == 0) {
            FareDto data = fareRes.getData();
            com.study.mall.feign.dto.MemberAddressDto address = data.getAddress();
            orderEntity.setFreightAmount(data.getFare());
            orderEntity.setReceiverDetailAddress(address.getDetailAddress());
            orderEntity.setReceiverName(address.getName());
            orderEntity.setReceiverPhone(address.getPhone());
            orderEntity.setReceiverPostCode(address.getPostCode());
            orderEntity.setReceiverProvince(address.getProvince());
            orderEntity.setReceiverRegion(address.getRegion());
        }
        return orderEntity;
    }

    private List<OrderItemEntity> buildOrderItems(String orderSn) {
        R<List<CartItemEntityDto>> cartItemsRes = cartFeignService.getCurrentUserCartItems();
        if (cartItemsRes.getCode() == 0) {
            List<CartItemEntityDto> cartItems = cartItemsRes.getData();
            if (Objects.nonNull(cartItems) && !cartItems.isEmpty()) {
                return cartItems.stream().map(cartItem -> {
                    OrderItemEntity orderItem = buildOrderItem(cartItem);
                    orderItem.setOrderSn(orderSn);
                    return orderItem;
                }).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    private OrderItemEntity buildOrderItem(CartItemEntityDto cartItem) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setSkuId(cartItem.getSkuId());
        orderItemEntity.setSkuName(cartItem.getTitle());
        orderItemEntity.setSkuPic(cartItem.getImage());
        orderItemEntity.setSkuPrice(cartItem.getPrice());
        orderItemEntity.setSkuAttrsVals(StringUtils.collectionToDelimitedString(cartItem.getSkuAttr(), ":"));
        orderItemEntity.setSkuQuantity(cartItem.getCount());
        orderItemEntity.setGiftGrowth(cartItem.getPrice().intValue());
        orderItemEntity.setGiftIntegration(cartItem.getPrice().intValue());
        Long skuId = cartItem.getSkuId();
        R<SpuInfoDto> spuRes = spuInfoFeignService.getInfoBySkuId(skuId);
        if (spuRes.getCode() == 0) {
            SpuInfoDto spuInfoDto = spuRes.getData();
            orderItemEntity.setSpuId(spuInfoDto.getId());
            orderItemEntity.setSpuBrand(spuInfoDto.getBrandId().toString());
            orderItemEntity.setSpuName(spuInfoDto.getSpuName());
            orderItemEntity.setSpuPic(cartItem.getImage());
            orderItemEntity.setCategoryId(spuInfoDto.getCatalogId());
        }
        orderItemEntity.setPromotionAmount(new BigDecimal(0));
        orderItemEntity.setCouponAmount(new BigDecimal(0));
        orderItemEntity.setIntegrationAmount(new BigDecimal(0));
        orderItemEntity.setRealAmount(orderItemEntity.getSkuPrice()
                .multiply(new BigDecimal(orderItemEntity.getSkuQuantity()))
                .subtract(orderItemEntity.getPromotionAmount())
                .subtract(orderItemEntity.getIntegrationAmount())
                .subtract(orderItemEntity.getIntegrationAmount())
        );
        return orderItemEntity;
    }

}