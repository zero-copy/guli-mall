package com.study.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/26 19:47
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MallSecKillApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSecKillApplication.class, args);
    }
}
