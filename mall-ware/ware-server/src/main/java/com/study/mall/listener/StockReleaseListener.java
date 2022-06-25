package com.study.mall.listener;

import com.alibaba.nacos.common.utils.Objects;
import com.rabbitmq.client.Channel;
import com.study.mall.common.dto.StockDetailDto;
import com.study.mall.common.dto.StockLockedDto;
import com.study.mall.common.lang.R;
import com.study.mall.dto.OrderEntityDto;
import com.study.mall.entity.WareOrderTaskDetailEntity;
import com.study.mall.entity.WareOrderTaskEntity;
import com.study.mall.feign.IOrderFeignService;
import com.study.mall.service.IWareOrderTaskDetailService;
import com.study.mall.service.IWareOrderTaskService;
import com.study.mall.service.IWareSkuService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/25 09:39
 */
@Component
@RabbitListener
public class StockReleaseListener {

    @Resource
    private IWareOrderTaskDetailService wareOrderTaskDetailService;

    @Resource
    private IWareOrderTaskService wareOrderTaskService;

    @Resource
    IOrderFeignService orderFeignService;

    @Resource
    IWareSkuService wareSkuService;

    @RabbitHandler
    public void stockLockReleaseHandler(StockLockedDto dto, Message message, Channel channel) throws IOException {
        StockDetailDto detail = dto.getDetail();
        WareOrderTaskDetailEntity dbDetail = wareOrderTaskDetailService.getById(detail.getId());
        //库存是否锁定成功
        if (Objects.nonNull(dbDetail)) {
            WareOrderTaskEntity orderTask = wareOrderTaskService.getById(dto.getId());
            //查询订单状态
            R<OrderEntityDto> orderRes = orderFeignService.getOrderStatus(orderTask.getOrderSn());
            if (orderRes.getCode() == 0) {
                OrderEntityDto orderDto = orderRes.getData();
                //没有订单 or 订单取消
                if (Objects.isNull(orderDto) || orderDto.getStatus() == 4) {
                    //解锁库存
                    wareSkuService.unLockStock(dbDetail.getSkuId(), dbDetail.getWareId(), dbDetail.getSkuNum(), orderTask.getId());
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                }
            } else {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }
        } else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
