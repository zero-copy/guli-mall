package com.study.mall.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Harlan
 * @date 2022 06 22 下午 05:48
 */
@Data
public class OrderSubmitVo implements Serializable {

    private Long addrId;

    private Integer payType;

    private String orderToken;

    private BigDecimal payPrice;

    private String note;
}
