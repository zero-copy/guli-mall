package com.study.mall.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/27 12:04
 */
@Configuration
public class MallRedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://redis-server:6379").setPassword("lb82ndLF");
        return Redisson.create(config);
    }
}
