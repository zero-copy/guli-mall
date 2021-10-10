package com.study.mall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Harlan
 * @date 2021 10 10 14:44
 */
@SpringBootApplication
@MapperScan(basePackages = "com.study.mall.order.mapper")
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }
}
