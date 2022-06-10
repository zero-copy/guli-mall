package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.dto.SendSmsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Harlan
 * @date 2022 06 09 下午 05:41
 */
@FeignClient(value = "mall-third", path = "/sms")
public interface SmsFeignService {

    /**
     * 发送验证码
     * @param phoneNum 手机号
     * @param code 验证码
     * @return 返回信息
     */
    @GetMapping("/send-code")
    R<SendSmsResponse> sendCode(@RequestParam("phoneNum") String phoneNum, @RequestParam("code") String code);
}
