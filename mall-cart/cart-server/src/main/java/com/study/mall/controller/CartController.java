package com.study.mall.controller;

import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.common.dto.TempUserInfo;
import com.study.mall.interceptor.CartInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author Harlan
 * @date 2022 06 17 下午 12:23
 */
@Controller
public class CartController {

    @GetMapping("/cart.html")
    public String cartPage(HttpSession session) {
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        TempUserInfo tempUserInfo = CartInterceptor.THREAD_LOCAL.get();
        if (Objects.isNull(attribute)) {
            //临时购物车
        } else {
            //登陆购物车
        }
        return "cartList";
    }
}
