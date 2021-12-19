package com.study.mall.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Harlan
 * @date 2021 12 19 下午 06:54
 */
@Slf4j
@Controller
public class ItemController {

    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable Long skuId) {
        log.info("skuId: " + skuId);
        return "item";
    }
}
