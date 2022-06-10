package com.study.mall.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harlan
 * @date 2022 06 09 下午 04:31
 */
@Configuration
public class SmsClientConfig {

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String secretKey;

    @Bean
    public Client smsClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessId)
                .setAccessKeySecret(secretKey);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
}
