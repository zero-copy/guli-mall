package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

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
    private Long brandId;

    /**
    * 分类id
    */
    private Long catelogId;

    /**
    * 
    */
    private String brandName;

    /**
    * 
    */
    private String catelogName;

        
    private static final String ID = "id";

    private static final String BRAND_ID = "brand_id";

    private static final String CATELOG_ID = "catelog_id";

    private static final String BRAND_NAME = "brand_name";

    private static final String CATELOG_NAME = "catelog_name";

    
}
