package com.study.mall.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.study.mall.config.AlipayTemplate;
import com.study.mall.service.IOrderService;
import com.study.mall.vo.PayAsyncVo;
import com.study.mall.vo.PayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/26 11:55
 */
@Slf4j
@Controller
@RequestMapping("/pay")
public class PayController {

    @Resource
    private AlipayTemplate alipayTemplate;

    @Resource
    private IOrderService orderService;

    @ResponseBody
    @GetMapping(value = "/aliPayOrder", produces = "text/html;charset=utf-8 ")
    public String payOrder(@RequestParam String orderSn) throws AlipayApiException {
        PayVo payVo = orderService.getOrderPay(orderSn);
        return alipayTemplate.pay(payVo);
    }

    @ResponseBody
    @PostMapping("/notify")
    public String aliNotify(PayAsyncVo vo, HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        //验证签名
        log.info("支付宝付款通知 orderSn: {}", vo.getOut_trade_no());
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(
                params,
                alipayTemplate.getAlipay_public_key(),
                alipayTemplate.getCharset(),
                alipayTemplate.getSign_type());
        if (signVerified) {
            log.info("支付宝付款成功 orderSn: {}", vo.getOut_trade_no());
            return orderService.handlePayResult(vo);
        }
        log.info("支付宝付款失败 orderSn: {}", vo.getOut_trade_no());
        return "error";
    }
}
