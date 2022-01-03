package com.study.mall.web;

import com.study.mall.service.ISkuInfoService;
import com.study.mall.vo.SkuItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author Harlan
 * @date 2021 12 19 下午 06:54
 */
@Slf4j
@Controller
public class ItemController {

    @Resource
    ISkuInfoService skuInfoService;

    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable Long skuId) {
        log.info("skuId: " + skuId);
        SkuItemVo skuItemVo = skuInfoService.item(skuId);
        return "item";
    }
}
