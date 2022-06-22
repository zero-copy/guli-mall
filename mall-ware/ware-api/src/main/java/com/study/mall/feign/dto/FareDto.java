package com.study.mall.feign.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Harlan
 * @date 2022 06 22 下午 07:04
 */
@Data
public class FareDto implements Serializable {


    private BigDecimal fare;

    private MemberAddressDto address;
}
