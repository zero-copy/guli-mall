package com.study.mall.dto;

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
public class SeckillSkuRelationDto implements Serializable {

    /**
    * id
    */
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
}
