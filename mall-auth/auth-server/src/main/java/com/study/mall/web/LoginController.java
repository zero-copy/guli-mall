package com.study.mall.web;

import cn.hutool.core.bean.BeanUtil;
import com.study.mall.common.lang.R;
import com.study.mall.dto.MemberLoginDto;
import com.study.mall.feign.IMemberFeignService;
import com.study.mall.form.UserLoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * @author Harlan
 * @date 2022 06 09 下午 01:24
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private IMemberFeignService memberFeignService;

    @PostMapping("/login")
    public String login(UserLoginForm userLoginForm, RedirectAttributes attributes) {
        MemberLoginDto loginDto = BeanUtil.copyProperties(userLoginForm, MemberLoginDto.class);
        R result = memberFeignService.login(loginDto);
        if (result.getCode() == 0) {
            //success
            return "redirect:http://gulimall.com";
        }
        attributes.addFlashAttribute("errors", result.getMsg());
        return "redirect:http://auth.gulimall.com/login.html";
    }
}
