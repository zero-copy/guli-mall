package com.study.mall.controller;

import com.study.mall.entity.CartEntity;
import com.study.mall.entity.CartItemEntity;
import com.study.mall.service.ICartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
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
    public String cartPage(Model model) {
        CartEntity cartEntity = cartService.getCart();
        model.addAttribute("cart", cartEntity);
        return "cartList";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam Long skuId, @RequestParam Integer num, RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {
        cartService.addToCart(skuId, num);
        redirectAttributes.addAttribute("skuId", skuId);
        return "redirect:http://cart.gulimall.com/addToCartSuccess.html";
    }

    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccessPage(@RequestParam Long skuId, Model model) {
        CartItemEntity cartItem = cartService.getCartItem(skuId);
        if (Objects.nonNull(cartItem)) {
            model.addAttribute(cartItem);
        }
        return "success";
    }
}
