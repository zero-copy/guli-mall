package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
        import java.math.BigDecimal;
    import lombok.Data;

/**
 * spu信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Data
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 商品id
    */
    @TableId
    private Long id;

    /**
    * 商品名称
    */
    private String spuName;

    /**
    * 商品描述
    */
    private String spuDescription;

    /**
    * 所属分类id
    */
    private Long catalogId;

    /**
    * 品牌id
    */
    private Long brandId;

    /**
    * 
    */
    private BigDecimal weight;

    /**
    * 上架状态[0 - 下架，1 - 上架]
    */
    private Integer publishStatus;

    /**
    * 
    */
    private LocalDateTime createTime;

    /**
    * 
    */
    private LocalDateTime updateTime;

        
    private static final String ID = "id";

    private static final String SPU_NAME = "spu_name";

    private static final String SPU_DESCRIPTION = "spu_description";

    private static final String CATALOG_ID = "catalog_id";

    private static final String BRAND_ID = "brand_id";

    private static final String WEIGHT = "weight";

    private static final String PUBLISH_STATUS = "publish_status";

    private static final String CREATE_TIME = "create_time";

    private static final String UPDATE_TIME = "update_time";

    
}
