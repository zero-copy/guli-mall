package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

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

        
    private static final String CAT_ID = "cat_id";

    private static final String NAME = "name";

    private static final String PARENT_CID = "parent_cid";

    private static final String CAT_LEVEL = "cat_level";

    private static final String SHOW_STATUS = "show_status";

    private static final String SORT = "sort";

    private static final String ICON = "icon";

    private static final String PRODUCT_UNIT = "product_unit";

    private static final String PRODUCT_COUNT = "product_count";

    
}
