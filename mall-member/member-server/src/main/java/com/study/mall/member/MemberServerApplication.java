package com.study.mall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Harlan
 * @date 2021 10 10 14:17
 */
@SpringBootApplication
@MapperScan(basePackages = "com.study.mall.member.mapper")
public class MemberServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberServerApplication.class, args);
    }
}
