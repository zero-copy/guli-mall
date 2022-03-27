package com.study.mall.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 03 27 下午 07:32
 */
@Data
public class SkuItemSaleAttrVo implements Serializable {

    private Long attrId;

    private String attrName;

    private String attrValues;
}