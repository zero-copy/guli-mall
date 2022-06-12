package com.study.mall.web;

import cn.hutool.core.bean.BeanUtil;
import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.common.enums.ErrorCodeEnum;
import com.study.mall.common.lang.R;
import com.study.mall.dto.MemberRegisterDto;
import com.study.mall.feign.IMemberFeignService;
import com.study.mall.feign.SmsFeignService;
import com.study.mall.form.UserRegisterForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Harlan
 * @date 2022 06 09 下午 01:26
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private IMemberFeignService memberFeignService;

    @Resource
    private SmsFeignService smsFeignService;

    private static final String[] NUMS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @ResponseBody
    @GetMapping("/send-code")
    public R sendCode(@RequestParam String phoneNum) {
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phoneNum);
        StringBuilder codeSb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            codeSb.append(NUMS[random.nextInt(NUMS.length)]);
        }
        if (org.apache.commons.lang.StringUtils.isBlank(redisCode)) {
            redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phoneNum, codeSb + "-" + System.currentTimeMillis(), 10, TimeUnit.MINUTES);
            smsFeignService.sendCode(phoneNum, codeSb.toString());
            return R.ok();
        } else {
            long time = Long.parseLong(redisCode.split("-")[1]);
            if (System.currentTimeMillis() - time < (60 * 1000)) {
                return R.error(ErrorCodeEnum.SMS_CODE_EXCEPTION.getCode(), ErrorCodeEnum.SMS_CODE_EXCEPTION.getMessage());
            } else {
                redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phoneNum, codeSb + "-" + System.currentTimeMillis(), 10, TimeUnit.MINUTES);
                smsFeignService.sendCode(phoneNum, codeSb.toString());
                return R.ok();
            }
        }
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterForm registerForm, BindingResult result, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(
                    Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
            );
            modelAndView.addObject("errors", errors);
            modelAndView.setViewName("reg");
        } else {
            HashMap<String, String> errors = new HashMap<>();
            modelAndView.addObject("errors", errors);
            String code = registerForm.getCode();
            String codeKey = AuthServerConstant.SMS_CODE_CACHE_PREFIX + registerForm.getPhoneNum();
            String redisCode = redisTemplate.opsForValue().get(codeKey);
            if (StringUtils.isNotBlank(redisCode)) {
                modelAndView.setViewName("redirect:http://auth:gulimall.com/login.html");
                if (redisCode.split("-")[0].equals(code)) {
                    //调用远程服务进行注册
                    redisTemplate.delete(codeKey);
                    MemberRegisterDto memberRegisterDto = BeanUtil.copyProperties(registerForm, MemberRegisterDto.class);
                    memberFeignService.register(memberRegisterDto);
                } else {
                    modelAndView.setViewName("reg");
                    errors.put("code", "验证码错误");
                }
            } else {
                //验证码过期
                modelAndView.setViewName("reg");
                errors.put("code", "验证码过期");
            }
        }
        return modelAndView;
    }
}
