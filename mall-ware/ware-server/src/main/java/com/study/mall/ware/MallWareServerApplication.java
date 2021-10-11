package com.study.mall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Harlan
 * @date 2021 10 10 16:36
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.study.mall.ware.mapper")
public class MallWareServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallWareServerApplication.class, args);
    }
}
