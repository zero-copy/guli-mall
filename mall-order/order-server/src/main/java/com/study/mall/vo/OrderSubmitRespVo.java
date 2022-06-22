package com.study.mall.vo;

import com.study.mall.entity.OrderEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 22 下午 06:40
 */
@Data
public class OrderSubmitRespVo implements Serializable {

    private OrderEntity orderEntity;

    private Integer code;
}
