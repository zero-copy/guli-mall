package com.study.mall.common.lang.dto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 10 30 下午 11:39
 */
@Data
public class SkuReductionDto implements Serializable {

    private Long skuId;

    private Integer fullCount;

    private BigDecimal discount;

    private Integer countStatus;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;

    private Integer priceStatus;

    private List<MemberPriceDto> memberPrice;
}
