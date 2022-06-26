package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
    
/**
 * 秒杀活动商品关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Data
@TableName("sms_seckill_sku_relation")
public class SeckillSkuRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 活动id
    */
    private Long promotionId;

    /**
    * 活动场次id
    */
    private Long promotionSessionId;

    /**
    * 商品id
    */
    private Long skuId;

    /**
    * 秒杀价格
    */
    private BigDecimal seckillPrice;

    /**
    * 秒杀总量
    */
    private Integer seckillCount;

    /**
    * 每人限购数量
    */
    private Integer seckillLimit;

    /**
    * 排序
    */
    private Integer seckillSort;

    
    public static final String ID = "id";

    public static final String PROMOTION_ID = "promotion_id";

    public static final String PROMOTION_SESSION_ID = "promotion_session_id";

    public static final String SKU_ID = "sku_id";

    public static final String SECKILL_PRICE = "seckill_price";

    public static final String SECKILL_COUNT = "seckill_count";

    public static final String SECKILL_LIMIT = "seckill_limit";

    public static final String SECKILL_SORT = "seckill_sort";

    
}
