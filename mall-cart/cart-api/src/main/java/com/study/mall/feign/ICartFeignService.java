package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.dto.CartItemEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Harlan
 * @date 2022 06 21 下午 02:05
 */
@FeignClient(contextId = "cart", value = "mall-cart")
public interface ICartFeignService {

    @GetMapping("/currentUserCartItems")
    R<List<CartItemEntityDto>> getCurrentUserCartItems();
}
