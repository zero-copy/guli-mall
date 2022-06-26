package com.study.mall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/26 12:21
 */
@Controller
public class MemberWebController {

    @GetMapping("/memberOrder.html")
    public String memberOrderPage() {
        return "orderList";
    }
}
