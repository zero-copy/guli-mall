package com.study.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Harlan
 * @date 2021 11 02 下午 11:16
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MallSearchServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSearchServerApplication.class, args);
    }
}
