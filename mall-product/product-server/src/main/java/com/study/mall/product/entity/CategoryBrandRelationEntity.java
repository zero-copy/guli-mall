package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 品牌分类关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @TableId
    private Long id;

    /**
    * 品牌id
    */
    @NotNull
    private Long brandId;

    /**
    * 分类id
    */
    @NotNull
    private Long catelogId;

    /**
    * 
    */
    private String brandName;

    /**
    * 
    */
    private String catelogName;

        
    public static final String ID = "id";

    public static final String BRAND_ID = "brand_id";

    public static final String CATELOG_ID = "catelog_id";

    public static final String BRAND_NAME = "brand_name";

    public static final String CATELOG_NAME = "catelog_name";

    
}
