package com.study.mall.feign;

import com.study.mall.common.lang.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Harlan
 * @date 2022 06 18 下午 12:02
 */
@FeignClient(contextId = "skuSaleAttr", value = "mall-product", path = "product/skusaleattrvalue")
public interface ISkuSaleAttrValueFeignService {

    @GetMapping("/string-list/{skuId}")
    R<List<String>> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId);
}
