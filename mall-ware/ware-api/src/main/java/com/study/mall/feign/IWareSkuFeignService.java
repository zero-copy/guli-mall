package com.study.mall.feign;

import com.study.mall.common.lang.dto.SkuStockDto;
import com.study.mall.common.lang.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 03 上午 01:05
 */
@FeignClient(value = "mall-ware", path = "/ware/waresku")
public interface IWareSkuFeignService {

    @PostMapping("/hasStock")
    R<List<SkuStockDto>> hasStock(@RequestBody List<Long> skuIds);
}
