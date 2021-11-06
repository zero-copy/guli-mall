package com.study.mall.common.lang.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Harlan
 * @date 2021 10 30 下午 11:30
 */
@Data
public class SpuBoundsDto implements Serializable {

    private Long spuId;

    private BigDecimal buyBounds;

    private BigDecimal growBounds;
}
