package com.study.mall.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Harlan
 * @date 2021 10 16 22:28
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallThirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallThirdApplication.class, args);
    }
}
