package com.study.mall.listener;

import com.rabbitmq.client.Channel;
import com.study.mall.common.dto.StockLockedDto;
import com.study.mall.dto.OrderEntityDto;
import com.study.mall.service.IWareSkuService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class StockReleaseListener {

    @Resource
    IWareSkuService wareSkuService;

    @RabbitListener(queues = {"stock.release.stock.queue"})
    @RabbitHandler
    public void handleStockLockRelease(StockLockedDto dto, Message message, Channel channel) throws IOException {
        log.info("解锁库存检查 detail-id: {}", dto.getDetail().getId());
        try {
            wareSkuService.unLockStock(dto);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    @RabbitListener(queues = {"stock.release.stock.queue"})
    @RabbitHandler
    public void handleOrderCloseRelease(OrderEntityDto orderDto, Message message, Channel channel) throws IOException {
        log.info("订单主动关闭-解锁库存检查 order-id: {}", orderDto.getId());
        try {
            wareSkuService.unLockStock(orderDto);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
