package com.study.common.config.swagger;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Harlan
 * @date 2021 10 16 15:16
 */
@Data
@ConfigurationProperties(prefix = "swagger2")
public class SwaggerProperties {

    /**
     * 包扫描路径
     */
    @Value("${base-package:com.study.mall.*.controller}")
    private String basePackage;

    /**
     * 作者
     */
    @Value("${author:HarlanHu}")
    private String author;

    /**
     * 作者主页
     */
    @Value("${url:https://github.com/zero-copy}")
    private String url;

    /**
     * 作者邮箱
     */
    @Value("${email:isharlan.hu@gmail.com}")
    private String email;

    /**
     * 标题
     */
    @Value("${title:mall}")
    private String title;

    /**
     * 描述
     */
    @Value("${description:谷粒商城项目}")
    private String description;

    /**
     * 版本
     */
    @Value("${version:0.0.1-SNAPSHOT}")
    private String version;

    /**
     * 服务地址
     */
    @Value("${http://47.108.151.199}")
    private String termsOfServiceUrl;
}
