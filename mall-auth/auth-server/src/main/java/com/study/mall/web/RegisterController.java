package com.study.mall.web;

import cn.hutool.core.bean.BeanUtil;
import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.dto.MemberRegisterDto;
import com.study.mall.feign.IMemberFeignService;
import com.study.mall.vo.UserRegisterVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Harlan
 * @date 2022 06 09 下午 01:26
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private IMemberFeignService memberFeignService;

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterVo registerVo, BindingResult result, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(
                    Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
            );
            modelAndView.addObject("errors", errors);
            modelAndView.setViewName("reg");
        } else {
            HashMap<String, String> errors = new HashMap<>();
            modelAndView.addObject("errors", errors);
            String code = registerVo.getCode();
            String codeKey = AuthServerConstant.SMS_CODE_CACHE_PREFIX + registerVo.getPhoneNum();
            String redisCode = redisTemplate.opsForValue().get(codeKey);
            if (StringUtils.isNotBlank(redisCode)) {
                modelAndView.setViewName("redirect:http://auth:gulimall.com/login.html");
                if (redisCode.split("-")[0].equals(code)) {
                    //调用远程服务进行注册
                    redisTemplate.delete(codeKey);
                    MemberRegisterDto memberRegisterDto = BeanUtil.copyProperties(registerVo, MemberRegisterDto.class);
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
