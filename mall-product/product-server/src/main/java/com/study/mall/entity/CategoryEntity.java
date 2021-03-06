package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品三级分类
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 分类id
    */
    @TableId
    private Long catId;

    /**
    * 分类名称
    */
    private String name;

    /**
    * 父分类id
    */
    private Long parentCid;

    /**
    * 层级
    */
    private Integer catLevel;

    /**
    * 是否显示[0-不显示，1显示]
    */
    @TableLogic(value = "1", delval = "0")
    private Integer showStatus;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 图标地址
    */
    private String icon;

    /**
    * 计量单位
    */
    private String productUnit;

    /**
    * 商品数量
    */
    private Integer productCount;

    /**
     * 子分类
     */
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<CategoryEntity> children;
        
    public static final String CAT_ID = "cat_id";

    public static final String NAME = "name";

    public static final String PARENT_CID = "parent_cid";

    public static final String CAT_LEVEL = "cat_level";

    public static final String SHOW_STATUS = "show_status";

    public static final String SORT = "sort";

    public static final String ICON = "icon";

    public static final String PRODUCT_UNIT = "product_unit";

    public static final String PRODUCT_COUNT = "product_count";

    
}
