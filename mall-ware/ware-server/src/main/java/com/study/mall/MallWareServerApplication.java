package com.study.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Harlan
 * @date 2021 10 10 16:36
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MallWareServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallWareServerApplication.class, args);
    }
}
