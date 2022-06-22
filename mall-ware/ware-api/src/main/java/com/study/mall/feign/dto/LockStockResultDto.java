package com.study.mall.feign.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 22 下午 09:00
 */
@Data
public class LockStockResultDto implements Serializable {

    private Long skuId;

    private Integer num;

    private Boolean locked;
}
