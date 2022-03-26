package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.common.lang.dto.SkuInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Harlan
 * @date 2021 11 01 下午 10:55
 */
@FeignClient(contextId = "skuinfo", value = "mall-product", path = "/product/skuinfo")
public interface ISkuInfoFeignService {

    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    R<SkuInfoDto> info(@PathVariable("skuId") Long skuId);
}
