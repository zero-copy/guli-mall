package com.study.mall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Harlan
 * @date 2021 10 10 14:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.study.mall.coupon.mapper")
public class MallCouponServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCouponServerApplication.class, args);
    }
}
