package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 商品属性
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_attr")
public class AttrEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 属性id
    */
    @TableId
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

        
    private static final String ATTR_ID = "attr_id";

    private static final String ATTR_NAME = "attr_name";

    private static final String SEARCH_TYPE = "search_type";

    private static final String VALUE_TYPE = "value_type";

    private static final String ICON = "icon";

    private static final String VALUE_SELECT = "value_select";

    private static final String ATTR_TYPE = "attr_type";

    private static final String ENABLE = "enable";

    private static final String CATELOG_ID = "catelog_id";

    private static final String SHOW_DESC = "show_desc";

    
}
