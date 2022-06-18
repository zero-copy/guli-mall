package com.study.mall.controller;

import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.common.dto.TempUserInfo;
import com.study.mall.entity.CartItemEntity;
import com.study.mall.interceptor.CartInterceptor;
import com.study.mall.service.ICartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * @author Harlan
 * @date 2022 06 17 下午 12:23
 */
@Controller
public class CartController {

    @Resource
    private ICartService cartService;

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

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam Long skuId, @RequestParam Integer num, Model model) throws ExecutionException, InterruptedException {
        cartService.addToCart(skuId, num);
        model.addAttribute("sukId", skuId);
        return "redirect:/addToCartSuccess.html";
    }

    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccessPage(@RequestParam Long skuId, Model model) {
        CartItemEntity cartItem = cartService.getCartItem(skuId);
        model.addAttribute(cartItem);
        return "success";
    }
}
