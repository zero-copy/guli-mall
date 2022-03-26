package com.study.mall.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2022 03 26 下午 08:10
 */
@Data
public class AttrDto implements Serializable {

    /**
     * 分类名
     */
    private String catelogName;

    /**
     * 分组名
     */
    private String groupName;

    /**
     * 分类路径
     */
    private List<Long> catelogPath;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Integer searchType;

    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    private Integer valueType;

    /**
     * 属性图标
     */
    private String icon;

    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;

    /**
     * 属性类型[0-销售属性，1-基本属性
     */
    private Integer attrType;

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Long enable;

    /**
     * 所属分类
     */
    private Long catelogId;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Integer showDesc;

    /**
     * 分组ID
     */
    private Long attrGroupId;
}
