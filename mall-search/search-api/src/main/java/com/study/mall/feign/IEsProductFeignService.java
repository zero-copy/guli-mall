package com.study.mall.feign;

import com.study.mall.common.dto.es.SkuEsDto;
import com.study.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 03 上午 01:47
 */
@FeignClient(value = "mall-search", path = "/search/save")
public interface IEsProductFeignService {

    @PostMapping("/product")
    R productStatusUp(@RequestBody List<SkuEsDto> skuEsDtos);
}
