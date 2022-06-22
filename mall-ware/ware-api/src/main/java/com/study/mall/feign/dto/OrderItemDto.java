package com.study.mall.feign.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Harlan
 * @date 2022 06 21 下午 01:28
 */
@Data
public class OrderItemDto implements Serializable {

    private Long skuId;

    private String title;

    private String image;

    private List<String> skuAttr;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(count));
    }
}
