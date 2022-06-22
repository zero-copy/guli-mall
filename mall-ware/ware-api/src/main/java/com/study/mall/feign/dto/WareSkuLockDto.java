package com.study.mall.feign.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2022 06 22 下午 08:29
 */
@Data
public class WareSkuLockDto implements Serializable {

    private String orderSn;

    private List<OrderItemDto> locks;
}
