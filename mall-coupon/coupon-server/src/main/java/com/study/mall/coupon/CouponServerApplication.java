package com.study.mall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Harlan
 * @date 2021 10 10 14:12
 */
@SpringBootApplication
@MapperScan(basePackages = "com.study.mall.coupon.mapper")
public class CouponServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponServerApplication.class, args);
    }
}
