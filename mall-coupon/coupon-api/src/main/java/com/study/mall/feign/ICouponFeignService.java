package com.study.mall.feign;

import com.study.mall.common.dto.SkuReductionDto;
import com.study.mall.common.dto.SpuBoundsDto;
import com.study.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Harlan
 * @date 2021 10 10 18:01
 */
@FeignClient(value = "mall-coupon", path = "/coupon")
public interface ICouponFeignService {

    @PostMapping("/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundsDto spuBoundsDto);

    @PostMapping("/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionDto reductionDto);
}
