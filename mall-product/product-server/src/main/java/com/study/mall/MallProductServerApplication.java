package com.study.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Harlan
 * @date 2021 10 10 12:00
 */
@SpringBootApplication(scanBasePackages = {"com.study.mall"})
@EnableRedisHttpSession
@EnableDiscoveryClient
@EnableFeignClients
public class MallProductServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductServerApplication.class, args);
    }
}
