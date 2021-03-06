package com.study.mall;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Harlan
 * @date 2021 10 10 14:44
 */
@SpringBootApplication(scanBasePackages = {"com.study.mall"})
@EnableRabbit
@EnableRedisHttpSession
@EnableFeignClients
@EnableDiscoveryClient
@EnableAutoDataSourceProxy
public class MallOrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderServerApplication.class, args);
    }
}
