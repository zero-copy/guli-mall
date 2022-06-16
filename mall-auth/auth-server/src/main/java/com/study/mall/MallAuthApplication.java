package com.study.mall;

import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Harlan
 * @date 2022 03 28 下午 05:57
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableRedissonHttpSession
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAuthApplication.class, args);
    }
}
