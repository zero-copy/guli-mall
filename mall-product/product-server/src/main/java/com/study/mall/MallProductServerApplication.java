package com.study.mall;

import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Harlan
 * @date 2021 10 10 12:00
 */
@SpringBootApplication(scanBasePackages = {"com.study.mall"})
@EnableRedissonHttpSession
@EnableDiscoveryClient
@EnableFeignClients
public class MallProductServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductServerApplication.class, args);
    }
}
