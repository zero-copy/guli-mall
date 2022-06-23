package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.feign.dto.FareDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/23 16:15
 */
@FeignClient(contextId = "ware-info", path = "/ware/wareinfo", value = "mall-ware")
public interface IWareInfoFeignService {

    @GetMapping("/fare")
    R<FareDto> getFare(@RequestParam("addrId") Long addrId);
}
