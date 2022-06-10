package com.study.mall.controller;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.study.mall.common.enums.ErrorCodeEnum;
import com.study.mall.common.lang.R;
import com.study.mall.util.SmsUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Harlan
 * @date 2022 06 09 下午 05:06
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private SmsUtil smsUtil;

    @GetMapping("/send-code")
    public R<SendSmsResponse> sendCode(@RequestParam String phoneNum, @RequestParam String code) {
        try {
            SendSmsResponse response = smsUtil.sendSmsCode(phoneNum, code);
            return R.ok(response);
        } catch (Exception e) {
            return R.error(ErrorCodeEnum.UNKNOWN_EXCEPTION.getCode(), e.getMessage());
        }
    }
}
