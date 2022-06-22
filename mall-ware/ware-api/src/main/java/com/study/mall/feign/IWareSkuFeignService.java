package com.study.mall.feign;

import com.study.mall.common.lang.dto.SkuStockDto;
import com.study.mall.common.lang.R;
import com.study.mall.feign.dto.FareDto;
import com.study.mall.feign.dto.LockStockResultDto;
import com.study.mall.feign.dto.WareSkuLockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 03 上午 01:05
 */
@FeignClient(value = "mall-ware", path = "/ware/waresku")
public interface IWareSkuFeignService {

    @PostMapping("/hasStock")
    R<List<SkuStockDto>> hasStock(@RequestBody List<Long> skuIds);

    @GetMapping("/fare")
    R<FareDto> getFare(@RequestParam Long addrId);

    @PostMapping("/lock/order")
    R<List<LockStockResultDto>> orderLockStock(@RequestBody WareSkuLockDto vo);
}
