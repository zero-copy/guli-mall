package com.study.mall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Harlan
 * @date 2021 10 10 16:36
 */
@SpringBootApplication
@MapperScan(basePackages = "com.study.mall.ware.mapper")
public class WareServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WareServerApplication.class, args);
    }
}
