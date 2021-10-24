package com.study.mall.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2021 10 24 下午 03:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AttrRespVo extends AttrReqVo implements Serializable {

    /**
     * 分类名
     */
    private String catelogName;

    /**
     * 分组名
     */
    private String groupName;
}
