package com.study.mall.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Harlan
 * @date 2021 10 10 18:01
 */
@FeignClient(value = "mall-coupon", path = "/coupon/coupon")
public interface ICouponFeignService {

}
