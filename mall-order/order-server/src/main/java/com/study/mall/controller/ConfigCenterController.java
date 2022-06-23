package com.study.mall.controller;

import com.study.mall.common.lang.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harlan
 * @date 2021 10 10 19:21
 */
@RefreshScope
@RestController
@RequestMapping("/order/config")
public class ConfigCenterController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active}")
    private String profilesActive;

    @Value("${config.version}")
    private String configVersion;

    @GetMapping("/info")
    public R info() {
        Map<String, Object> infoMap = new HashMap<>(4);
        infoMap.put("port", port);
        infoMap.put("application-name", applicationName);
        infoMap.put("profiles-active", profilesActive);
        infoMap.put("config-version", configVersion);
        return R.put("data", infoMap);
    }
}
