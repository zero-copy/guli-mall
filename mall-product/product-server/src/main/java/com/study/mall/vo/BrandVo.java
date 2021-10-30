package com.study.mall.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2021 10 27 下午 11:46
 */
@Data
public class BrandVo implements Serializable {

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 品牌名
     */
    private String brandName;
}
