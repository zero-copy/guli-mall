package com.study.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Harlan
 * @date 2022 03 27 下午 09:22
 */
@Primary
@Data
@Component
@ConfigurationProperties(prefix = "thread")
public class ThreadPoolProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAlive;
}
