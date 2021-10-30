package com.study.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Harlan
 * @date 2021 10 10 14:17
 */
@SpringBootApplication(scanBasePackages = {"com.study.mall"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.study.mall.feign")
public class MallMemberServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMemberServerApplication.class, args);
    }
}
