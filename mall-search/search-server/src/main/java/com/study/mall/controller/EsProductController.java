package com.study.mall.controller;

import com.study.mall.common.dto.es.SkuEsDto;
import com.study.mall.common.enums.ErrorCodeEnum;
import com.study.mall.common.utils.R;
import com.study.mall.product.IProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 03 上午 01:28
 */
@Slf4j
@RestController
@RequestMapping("/search/save")
public class EsProductController {

    @Resource
    private IProductSaveService productSaveService;

    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsDto> skuEsDtos) {
        boolean isSuccessful;
        try {
            isSuccessful = productSaveService.statusUp(skuEsDtos);
        } catch (IOException e) {
            log.error("ElasticSearch 错误: {} --- {} --- {}", e.getClass(), e.getMessage(), e.getStackTrace());
            isSuccessful = false;
        }
        return isSuccessful ? R.ok() : R.error(ErrorCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), ErrorCodeEnum.PRODUCT_UP_EXCEPTION.getMessage());
    }
}
