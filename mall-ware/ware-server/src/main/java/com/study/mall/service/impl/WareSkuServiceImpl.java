package com.study.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.nacos.common.utils.Objects;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.dto.StockDetailDto;
import com.study.mall.common.dto.StockLockedDto;
import com.study.mall.common.exception.MallException;
import com.study.mall.common.lang.R;
import com.study.mall.common.lang.dto.SkuInfoDto;
import com.study.mall.common.lang.dto.SkuStockDto;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.dto.OrderEntityDto;
import com.study.mall.entity.WareOrderTaskDetailEntity;
import com.study.mall.entity.WareOrderTaskEntity;
import com.study.mall.entity.WareSkuEntity;
import com.study.mall.exception.NoStockException;
import com.study.mall.feign.IOrderFeignService;
import com.study.mall.feign.ISkuInfoFeignService;
import com.study.mall.mapper.WareSkuMapper;
import com.study.mall.service.IWareOrderTaskDetailService;
import com.study.mall.service.IWareOrderTaskService;
import com.study.mall.service.IWareSkuService;
import com.study.mall.vo.OrderItemVo;
import com.study.mall.vo.SkuWareHasStock;
import com.study.mall.vo.WareSkuLockVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品库存
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@Service("wareSkuService")
@RabbitListener(queues = {"stock.release.stock.queue"})
@Transactional(rollbackFor = Exception.class)
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSkuEntity> implements IWareSkuService {

    @Resource
    private WareSkuMapper wareSkuMapper;

    @Resource
    private ISkuInfoFeignService skuInfoFeignService;

    @Resource
    private IWareOrderTaskDetailService wareOrderTaskDetailService;

    @Resource
    private IWareOrderTaskService wareOrderTaskService;

    @Resource
    IOrderFeignService orderFeignService;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (StringUtils.isNotBlank(skuId)) {
            wrapper.eq(WareSkuEntity.SKU_ID, skuId);
        }
        String wareId = (String) params.get("wareId");
        if (StringUtils.isNotBlank(wareId)) {
            wrapper.eq(WareSkuEntity.WARE_ID, wareId);
        }
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public boolean addStock(Long skuId, Long wareId, Integer skuNum) {
        List<WareSkuEntity> wareSkuEntities = wareSkuMapper.selectList(new QueryWrapper<WareSkuEntity>().eq(WareSkuEntity.SKU_ID, skuId).eq(WareSkuEntity.WARE_ID, wareId));
        if (wareSkuEntities.isEmpty()) {
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setWareId(wareId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setStockLocked(0);
            R<SkuInfoDto> response = skuInfoFeignService.info(skuId);
            if (response.getCode() == 0) {
                wareSkuEntity.setSkuName(response.getData().getSkuName());
            }
            return save(wareSkuEntity);
        } else {
            return wareSkuMapper.addStock(skuId, wareId, skuNum) != 0;
        }
    }

    @Override
    public List<SkuStockDto> getStockByIds(List<Long> skuIds) {
        return skuIds.stream().map(id ->{
            Long stock = baseMapper.getStock(id);
            SkuStockDto vo = new SkuStockDto();
            vo.setSkuId(id);
            if (Objects.isNull(stock)) {
                vo.setHasStock(false);
            } else {
                vo.setHasStock(stock > 0);
            }
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = NoStockException.class)
    public Boolean orderLockStock(WareSkuLockVo vo) {
        WareOrderTaskEntity orderTaskEntity = new WareOrderTaskEntity();
        orderTaskEntity.setOrderSn(vo.getOrderSn());
        orderTaskEntity.setOrderId(vo.getOrderId());
        wareOrderTaskService.save(orderTaskEntity);
        List<OrderItemVo> locks = vo.getLocks();
        List<SkuWareHasStock> wareHasStocks = locks.stream().map(item -> {
            SkuWareHasStock wareHasStock = new SkuWareHasStock();
            List<Long> wareIds = wareSkuMapper.listWareIdHasStock(item.getSkuId());
            wareHasStock.setSkuId(item.getSkuId());
            wareHasStock.setNum(item.getCount());
            wareHasStock.setWareIds(wareIds);
            return wareHasStock;
        }).collect(Collectors.toList());
        for (SkuWareHasStock wareHasStock : wareHasStocks) {
            boolean skuStock = false;
            Long skuId = wareHasStock.getSkuId();
            List<Long> wareIds = wareHasStock.getWareIds();
            if (wareIds.isEmpty()) {
                //没有库存
                throw new NoStockException(skuId);
            }
            for (Long wareId : wareIds) {
                Long count = wareSkuMapper.lockSkuStock(skuId, wareId, wareHasStock.getNum());
                if (count == 1) {
                    skuStock = true;
                    WareOrderTaskDetailEntity orderDetailEntity = new WareOrderTaskDetailEntity();
                    orderDetailEntity.setSkuId(skuId);
                    orderDetailEntity.setSkuNum(wareHasStock.getNum());
                    orderDetailEntity.setTaskId(orderTaskEntity.getId());
                    orderDetailEntity.setWareId(wareId);
                    orderDetailEntity.setLockStatus(1);
                    wareOrderTaskDetailService.save(orderDetailEntity);
                    StockLockedDto lockedDto = new StockLockedDto();
                    lockedDto.setId(orderTaskEntity.getId());
                    lockedDto.setDetail(BeanUtil.copyProperties(orderDetailEntity, StockDetailDto.class));
                    rabbitTemplate.convertAndSend("stock-event-exchange", "stock.locked", lockedDto);
                    break;
                }
            }
            if (Boolean.FALSE.equals(skuStock)) {
                throw new NoStockException(skuId);
            }
        }
        return true;
    }

    @Override
    public void unLockStock(StockLockedDto dto) {
        StockDetailDto detail = dto.getDetail();
        WareOrderTaskDetailEntity dbDetail = wareOrderTaskDetailService.getById(detail.getId());
        //库存是否锁定成功
        if (Objects.nonNull(dbDetail)) {
            WareOrderTaskEntity orderTask = wareOrderTaskService.getById(dto.getId());
            //查询订单状态
            R<OrderEntityDto> orderRes = orderFeignService.getOrderStatus(orderTask.getOrderSn());
            if (orderRes.getCode() == 0) {
                OrderEntityDto orderDto = orderRes.getData();
                //没有订单 or 订单取消 且 状态已锁定
                boolean hasStock = (Objects.isNull(orderDto) || orderDto.getStatus() == 4) && dbDetail.getLockStatus() == 1;
                if (hasStock) {
                    //解锁库存
                    wareSkuMapper.unLockStock(dbDetail.getSkuId(), dbDetail.getWareId(), dbDetail.getSkuNum(), orderTask.getId());
                    //修改详情状态
                    dbDetail.setLockStatus(2);
                    wareOrderTaskService.getById(dbDetail);
                }
            } else {
                throw new MallException("查询订单失败");
            }
        }
    }

    @Override
    public void unLockStock(OrderEntityDto orderDto) {
        WareOrderTaskEntity taskEntity = wareOrderTaskService.getByOrderSn(orderDto.getOrderSn());
        List<WareOrderTaskDetailEntity> details = wareOrderTaskDetailService.list(new QueryWrapper<WareOrderTaskDetailEntity>()
                .eq(WareOrderTaskDetailEntity.TASK_ID, taskEntity.getId())
                .eq(WareOrderTaskDetailEntity.LOCK_STATUS, 1));
        for (WareOrderTaskDetailEntity detail : details) {
            detail.setLockStatus(2);
            baseMapper.unLockStock(detail.getSkuId(), detail.getWareId(), detail.getSkuNum(), taskEntity.getId());
        }
        wareOrderTaskDetailService.updateBatchById(details);
    }

}