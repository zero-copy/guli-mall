package com.study.mall.feign;

import com.study.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Harlan
 * @date 2021 11 01 下午 10:55
 */
@FeignClient(value = "mall-product", path = "/product/skuinfo")
public interface ISkuInfoFeignService {

    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    R info(@PathVariable("skuId") Long skuId);
}
