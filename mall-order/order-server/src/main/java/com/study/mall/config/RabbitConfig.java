package com.study.mall.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/25 15:57
 */
@Slf4j
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        /**
         * 投递到exchange回调
         * correlationData 当前消息唯一id
         * ack 消息是否成功投递
         * cause 失败原因
         */
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                if (Objects.nonNull(correlationData) && Objects.nonNull(correlationData.getReturned())) {
                    log.warn("========消息投递失败========\n" +
                            "id: {}\n" +
                            "body: {}\n" +
                            "case: {}\n" +
                            "===========================",
                            correlationData.getReturned().getMessage().getMessageProperties().getDeliveryTag(),
                            correlationData.getReturned().getMessage().getBody(), cause);
                } else {
                    log.info("========消息投递失败========\n" +
                            "消息丢失\n" +
                            "case: {}" +
                            "===========================", cause);
                }

            }
        });

        /**
         * 投递到 Queque 回调
         */
        template.setReturnsCallback(returnedMessage -> log.info(
                "========消息投递成功========\n" +
                "id: {}\n" +
                "reply-code: {}\n" +
                "===========================",
                returnedMessage.getMessage().getMessageProperties().getDeliveryTag(),
                returnedMessage.getReplyCode()));
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
