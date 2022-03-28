package com.study.mall.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Harlan
 * @date 2022 03 27 下午 09:18
 */
@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadConfig {

    @Bean
    @Primary
    public ThreadPoolExecutor threadPool(ThreadPoolProperties poolProperties) {
        return new ThreadPoolExecutor(poolProperties.getCoreSize(), poolProperties.getMaxSize(),
                poolProperties.getKeepAlive(), TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
}
