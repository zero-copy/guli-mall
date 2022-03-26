package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.dto.AttrDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Harlan
 * @date 2022 03 26 下午 07:59
 */
@FeignClient(contextId = "attr", value = "mall-product", path = "/product/attr")
public interface IProductFeignService {

    @GetMapping("/info/{attrId}")
    R<AttrDto> getInfo(@PathVariable("attrId") Long attrId);
}
