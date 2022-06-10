package com.study.mall.web;

import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.common.enums.ErrorCodeEnum;
import com.study.mall.common.lang.R;
import com.study.mall.feign.SmsFeignService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Harlan
 * @date 2022 06 09 下午 01:24
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private SmsFeignService smsFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String[] NUMS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @GetMapping("/send-code")
    public R sendCode(@RequestParam String phoneNum) {
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phoneNum);
        StringBuilder codeSb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            codeSb.append(NUMS[random.nextInt(NUMS.length)]);
        }
        if (StringUtils.isBlank(redisCode)) {
            redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phoneNum, codeSb.toString() + "-" + System.currentTimeMillis(), 10, TimeUnit.MINUTES);
            smsFeignService.sendCode(phoneNum, codeSb.toString());
            return R.ok();
        } else {
            long time = Long.parseLong(redisCode.split("-")[1]);
            if (System.currentTimeMillis() - time < (60 * 1000)) {
                return R.error(ErrorCodeEnum.SMS_CODE_EXCEPTION.getCode(), ErrorCodeEnum.SMS_CODE_EXCEPTION.getMessage());
            } else {
                redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phoneNum, codeSb.toString() + "-" + System.currentTimeMillis(), 10, TimeUnit.MINUTES);
                smsFeignService.sendCode(phoneNum, codeSb.toString());
                return R.ok();
            }
        }
    }

}
