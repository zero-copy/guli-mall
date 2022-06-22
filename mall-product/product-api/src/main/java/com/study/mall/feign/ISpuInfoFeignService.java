package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.dto.SpuInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Harlan
 * @date 2022 06 22 下午 07:42
 */
@FeignClient(contextId = "spuinfo", value = "mall-product", path = "product/spuinfo")
public interface ISpuInfoFeignService {

    @GetMapping("/skuId/{id}")
    R<SpuInfoDto> getInfoBySkuId(@PathVariable("id") Long id);
}
