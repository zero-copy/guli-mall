package com.study.mall.vo;

import com.study.mall.dto.MemberAddressDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Harlan
 * @date 2022 06 22 下午 05:07
 */
@Data
public class FareVo implements Serializable {

    private BigDecimal fare;

    private MemberAddressDto address;
}
