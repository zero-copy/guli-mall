package com.study.mall.util;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Harlan
 * @date 2022 06 09 下午 05:00
 */
@Component
public class SmsUtil {

    @Resource
    private Client smsClient;

    public SendSmsResponse sendSmsCode(String phoneNum, String code) throws Exception {
        SendSmsRequest request = new SendSmsRequest()
                .setPhoneNumbers(phoneNum)
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setTemplateParam("{\"code\": \"" + code +"\"}");
        return smsClient.sendSms(request);
    }
}
