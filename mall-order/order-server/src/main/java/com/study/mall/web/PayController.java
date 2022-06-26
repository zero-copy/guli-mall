package com.study.mall.web;

import com.alipay.api.AlipayApiException;
import com.study.mall.config.AlipayTemplate;
import com.study.mall.service.IOrderService;
import com.study.mall.vo.PayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/26 11:55
 */
@Slf4j
@Controller
public class PayController {

    @Resource
    private AlipayTemplate alipayTemplate;

    @Resource
    private IOrderService orderService;

    @GetMapping(value = "/payOrder", produces = "text/html")
    public String payOrder(@RequestParam String orderSn) throws AlipayApiException {
        PayVo payVo = orderService.getOrderPay(orderSn);
        return alipayTemplate.pay(payVo);
    }
}
