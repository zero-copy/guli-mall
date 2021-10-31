package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

        
    public static final String ID = "id";

    public static final String SPU_NAME = "spu_name";

    public static final String SPU_DESCRIPTION = "spu_description";

    public static final String CATALOG_ID = "catalog_id";

    public static final String BRAND_ID = "brand_id";

    public static final String WEIGHT = "weight";

    public static final String PUBLISH_STATUS = "publish_status";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    
}
