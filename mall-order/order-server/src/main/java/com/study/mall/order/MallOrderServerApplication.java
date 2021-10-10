package com.study.mall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Harlan
 * @date 2021 10 10 14:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.study.mall.order.mapper")
public class MallOrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderServerApplication.class, args);
    }
}
