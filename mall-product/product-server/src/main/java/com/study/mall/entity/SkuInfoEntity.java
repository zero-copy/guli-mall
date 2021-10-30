package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
        import java.math.BigDecimal;
    import lombok.Data;

/**
 * sku信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Data
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * skuId
    */
    @TableId
    private Long skuId;

    /**
    * spuId
    */
    private Long spuId;

    /**
    * sku名称
    */
    private String skuName;

    /**
    * sku介绍描述
    */
    private String skuDesc;

    /**
    * 所属分类id
    */
    private Long catalogId;

    /**
    * 品牌id
    */
    private Long brandId;

    /**
    * 默认图片
    */
    private String skuDefaultImg;

    /**
    * 标题
    */
    private String skuTitle;

    /**
    * 副标题
    */
    private String skuSubtitle;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 销量
    */
    private Long saleCount;

        
    private static final String SKU_ID = "sku_id";

    private static final String SPU_ID = "spu_id";

    private static final String SKU_NAME = "sku_name";

    private static final String SKU_DESC = "sku_desc";

    private static final String CATALOG_ID = "catalog_id";

    private static final String BRAND_ID = "brand_id";

    private static final String SKU_DEFAULT_IMG = "sku_default_img";

    private static final String SKU_TITLE = "sku_title";

    private static final String SKU_SUBTITLE = "sku_subtitle";

    private static final String PRICE = "price";

    private static final String SALE_COUNT = "sale_count";

    
}
