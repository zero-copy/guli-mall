package com.study.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Harlan
 * @date 2021 10 10 12:00
 */
@SpringBootApplication(scanBasePackages = {"com.study.mall"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.study.mall.product.mapper")
public class MallProductServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductServerApplication.class, args);
    }
}
