package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.dto.MemberEntityDto;
import com.study.mall.common.lang.R;
import com.study.mall.common.lang.dto.SkuStockDto;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.dto.CartItemEntityDto;
import com.study.mall.dto.MemberAddressDto;
import com.study.mall.entity.OrderEntity;
import com.study.mall.feign.ICartFeignService;
import com.study.mall.feign.IMemberAddressFeignService;
import com.study.mall.feign.IWareSkuFeignService;
import com.study.mall.interceptor.LoginInterceptor;
import com.study.mall.mapper.OrderMapper;
import com.study.mall.service.IOrderService;
import com.study.mall.vo.OrderConfirmVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
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

    @Resource
    private IMemberAddressFeignService addressFeignService;

    @Resource
    private ICartFeignService cartFeignService;

    @Resource
    private IWareSkuFeignService wareSkuFeignService;

    @Resource
    private ThreadPoolExecutor threadPool;

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
        //TODO:防重令牌
        return confirmVo;
    }

}