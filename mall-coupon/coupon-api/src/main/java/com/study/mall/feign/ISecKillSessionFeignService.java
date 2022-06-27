package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.dto.SeckillSessionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/27 11:14
 */
@FeignClient(contextId = "sec-kill-session", value = "mall-coupon", path = "/coupon/seckillsession")
public interface ISecKillSessionFeignService {

    @GetMapping("/lastSession")
    R<List<SeckillSessionDto>> getLastThreeDaySession();
}
