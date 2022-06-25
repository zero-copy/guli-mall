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
@RabbitListener
public class StockReleaseListener {

    @Resource
    IWareSkuService wareSkuService;

    @RabbitHandler
    public void stockLockReleaseHandler(StockLockedDto dto, Message message, Channel channel) throws IOException {
        try {
            wareSkuService.unLockStock(dto);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
