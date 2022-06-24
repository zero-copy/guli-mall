package com.study.mall.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 24 下午 05:02
 */
@Data
public class StockLockedDto implements Serializable {

    private Long id;

    private StockDetailDto detail;
}
