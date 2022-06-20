package com.study.mall.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Harlan
 * @date 2022 06 20 下午 04:07
 */
@Configuration
public class RabbitMqConfig {

    @Resource
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        /**
         * 投递到exchange回调
         * correlationData 当前消息唯一id
         * ack 消息是否成功投递
         * cause 失败原因
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

        });

        /**
         * 投递到 Queque 回调
         */
        rabbitTemplate.setReturnsCallback(returnedMessage -> {

        });
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
