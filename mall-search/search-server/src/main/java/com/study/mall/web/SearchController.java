package com.study.mall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Harlan
 * @date 2021 11 21 下午 04:54
 */
@Controller
public class SearchController {

    @GetMapping("/list.html")
    public String listPage() {
        return "list";
    }
}
