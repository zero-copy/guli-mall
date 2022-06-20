package com.study.mall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Harlan
 * @date 2022 06 20 下午 08:39
 */
@Controller
public class OrderWebController {

    @GetMapping("/toTrade")
    public String toTrade() {
        return "confirm";
    }
}
