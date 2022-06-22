package com.study.mall.dto;

import com.study.mall.entity.OrderEntity;
import com.study.mall.entity.OrderItemEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Harlan
 * @date 2022 06 22 下午 06:57
 */
@Data
public class OrderCreateDto implements Serializable {

    private OrderEntity order;

    private List<OrderItemEntity> orderItems;

    private BigDecimal payPrice;

    private BigDecimal fare;
}
