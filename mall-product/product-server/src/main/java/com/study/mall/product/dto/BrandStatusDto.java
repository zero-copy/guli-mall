package com.study.mall.product.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Harlan
 * @date 2021 10 16 18:36
 */
@Data
public class BrandStatusDto implements Serializable {

    /**
     * 品牌id
     */
    @NotNull
    private Long brandId;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    @NotNull
    private Integer showStatus;
}
