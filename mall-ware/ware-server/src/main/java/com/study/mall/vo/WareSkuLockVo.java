package com.study.mall.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2022 06 22 下午 08:29
 */
@Data
public class WareSkuLockVo implements Serializable {

    private String orderSn;

    private Long orderId;

    private List<OrderItemVo> locks;
}
