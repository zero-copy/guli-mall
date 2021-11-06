package com.study.mall.common.lang.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2021 11 03 上午 12:57
 */
@Data
public class SkuStockDto implements Serializable {

    private Long skuId;

    private Boolean hasStock;
}
