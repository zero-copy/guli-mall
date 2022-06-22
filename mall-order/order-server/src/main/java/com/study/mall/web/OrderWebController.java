package com.study.mall.web;

import com.study.mall.service.IOrderService;
import com.study.mall.vo.OrderConfirmVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author Harlan
 * @date 2022 06 20 下午 08:39
 */
@Controller
public class OrderWebController {

    @Resource
    private IOrderService orderService;

    @GetMapping("/toTrade")
    public String toTrade(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("confirmOrderData", confirmVo);
        return "confirm";
    }
}
