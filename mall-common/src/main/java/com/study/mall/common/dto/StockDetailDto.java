package com.study.mall.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 24 下午 05:11
 */
@Data
public class StockDetailDto implements Serializable {

    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * sku_name
     */
    private String skuName;

    /**
     * 购买个数
     */
    private Integer skuNum;

    /**
     * 工作单id
     */
    private Long taskId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 1-已锁定  2-已解锁  3-扣减
     */
    private Integer lockStatus;
}
