package com.study.mall.web;

import com.study.mall.vo.UserRegisterVo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Harlan
 * @date 2022 06 09 下午 01:26
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterVo registerVo, BindingResult result, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(
                    Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
            );
            modelAndView.addObject("errors", errors);
            modelAndView.setViewName("reg");
        } else {
            //调用远程服务进行注册
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }
}
