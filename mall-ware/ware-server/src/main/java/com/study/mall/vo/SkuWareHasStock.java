package com.study.mall.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2022 06 22 下午 09:17
 */
@Data
public
class SkuWareHasStock implements Serializable {

    private Long skuId;

    private List<Long> wareIds;
}