package com.study.mall.dto;

import com.study.mall.common.lang.dto.SkuInfoDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/27 11:43
 */
@Data
public class SecKillSkuRedisDto implements Serializable {

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

    private SkuInfoDto skuInfo;
}
