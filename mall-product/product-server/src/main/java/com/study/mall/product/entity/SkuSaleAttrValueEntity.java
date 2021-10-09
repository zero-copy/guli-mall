package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

/**
 * sku销售属性&值
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Data
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * sku_id
    */
    private Long skuId;

    /**
    * attr_id
    */
    private Long attrId;

    /**
    * 销售属性名
    */
    private String attrName;

    /**
    * 销售属性值
    */
    private String attrValue;

    /**
    * 顺序
    */
    private Integer attrSort;

        
    private static final String ID = "id";

    private static final String SKU_ID = "sku_id";

    private static final String ATTR_ID = "attr_id";

    private static final String ATTR_NAME = "attr_name";

    private static final String ATTR_VALUE = "attr_value";

    private static final String ATTR_SORT = "attr_sort";

    
}
