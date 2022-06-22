package com.study.mall.web;

import com.study.mall.service.IOrderService;
import com.study.mall.vo.OrderConfirmVo;
import com.study.mall.vo.OrderSubmitRespVo;
import com.study.mall.vo.OrderSubmitVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author Harlan
 * @date 2022 06 20 下午 08:39
 */
@Controller
@Slf4j
public class OrderWebController {

    @Resource
    private IOrderService orderService;

    @GetMapping("/toTrade")
    public String toTrade(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("confirmOrderData", confirmVo);
        return "confirm";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo submit) {
        log.info(submit.toString());
        OrderSubmitRespVo vo = orderService.submitOrder(submit);
        if (vo.getCode() == 0) {
            return "pay";
        } else {
            return "redirect:http://order.gulimall.com/toTrade";
        }
    }
}
