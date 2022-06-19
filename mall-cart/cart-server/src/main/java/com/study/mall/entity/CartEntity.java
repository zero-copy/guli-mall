package com.study.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Harlan
 * @date 2022 06 17 上午 11:54
 */
@Data
public class CartEntity implements Serializable {

    private BigDecimal reduce = new BigDecimal(0);

    private BigDecimal totalAmount = new BigDecimal(0);

    private Integer countType = 0;

    private Integer countNum = 0;

    private List<CartItemEntity> items = Collections.emptyList();

    public BigDecimal getTotalAmount() {
        if (Objects.nonNull(items) && !items.isEmpty()) {
            items.forEach(item -> {
                if (Boolean.TRUE.equals(item.getChecked())) {
                    totalAmount = totalAmount.add(item.getTotalPrice());
                }
            });
        }
        totalAmount = totalAmount.subtract(getReduce());
        return totalAmount;
    }

    public Integer getCountType() {
        if (Objects.nonNull(items) && !items.isEmpty()) {
            items.forEach(item -> {
                if (Boolean.TRUE.equals(item.getChecked())) {
                    countType += 1;
                }
            });
        }
        return countType;
    }

    public Integer getCountNum() {
        if (Objects.nonNull(items) && !items.isEmpty()) {
            items.forEach(item -> {
                if (Boolean.TRUE.equals(item.getChecked())) {
                    countNum += item.getCount();
                }
            });
        }
        return countNum;
    }
}
