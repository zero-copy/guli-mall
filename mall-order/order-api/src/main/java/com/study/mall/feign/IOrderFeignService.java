package com.study.mall.feign;

import com.study.mall.common.lang.R;
import com.study.mall.dto.OrderEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/25 08:53
 */
@FeignClient(contextId = "order", value = "mall-order", path = "/order/order")
public interface IOrderFeignService {

    /**
     * 通过订单号查询订单
     * @param orderSn 订单号
     * @return 订单信息
     */
    @GetMapping("/status/{orderSn}")
    R<OrderEntityDto> getOrderStatus(@PathVariable("orderSn") String orderSn);
}
