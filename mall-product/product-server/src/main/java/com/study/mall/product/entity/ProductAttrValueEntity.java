package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

/**
 * spu属性值
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_product_attr_value")
public class ProductAttrValueEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 商品id
    */
    private Long spuId;

    /**
    * 属性id
    */
    private Long attrId;

    /**
    * 属性名
    */
    private String attrName;

    /**
    * 属性值
    */
    private String attrValue;

    /**
    * 顺序
    */
    private Integer attrSort;

    /**
    * 快速展示【是否展示在介绍上；0-否 1-是】
    */
    private Integer quickShow;

        
    private static final String ID = "id";

    private static final String SPU_ID = "spu_id";

    private static final String ATTR_ID = "attr_id";

    private static final String ATTR_NAME = "attr_name";

    private static final String ATTR_VALUE = "attr_value";

    private static final String ATTR_SORT = "attr_sort";

    private static final String QUICK_SHOW = "quick_show";

    
}
