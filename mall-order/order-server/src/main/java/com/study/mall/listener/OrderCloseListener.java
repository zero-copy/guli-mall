package com.study.mall.listener;

import com.rabbitmq.client.Channel;
import com.study.mall.entity.OrderEntity;
import com.study.mall.service.IOrderService;
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
 * @date 2022/6/25 11:24
 */
@Slf4j
@Component
@RabbitListener(queues = {"order.release.order.queue"})
public class OrderCloseListener {

    @Resource
    IOrderService orderService;

    @RabbitHandler
    public void handleOrderClose(OrderEntity order, Message message, Channel channel) throws IOException {
        log.info("关单检查 order-id: {}", order.getId());
        try {
            orderService.closeOrder(order);
            //调用支付宝收单 api (没做)
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
