package com.study.mall.vo;

import com.study.mall.dto.CartItemEntityDto;
import com.study.mall.dto.MemberAddressDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Harlan
 * @date 2022 06 21 下午 01:19
 */
@Data
public class OrderConfirmVo implements Serializable {

    /**
     * 防重令牌
     */
    private String orderToken;

    /**
     * 地址
     */
    private List<MemberAddressDto> address;

    /**
     * 购物项
     */
    private List<CartItemEntityDto> items;

    /**
     * 积分
     */
    private Integer integration = 0;

    /**
     * 订单总额
     */
    private BigDecimal total = new BigDecimal(0);

    /**
     * 应付
     */
    private BigDecimal payPrice = new BigDecimal(0);

    private Integer count = 0;

    private Map<Long, Boolean> stocks;

    private BigDecimal weight;

    public BigDecimal getTotal() {
        BigDecimal tempTotal = new BigDecimal(0);
        if (Objects.nonNull(items)) {
            for (CartItemEntityDto item : items) {
                tempTotal = tempTotal.add(item.getTotalPrice());
            }
            this.total = tempTotal;
        }
        return total;
    }

    public BigDecimal getPayPrice() {
        return getTotal();
    }

    public Integer getCount() {
        int tempCount = 0;
        if (Objects.nonNull(items)) {
            for (CartItemEntityDto item : items) {
                tempCount += item.getCount();
            }
            this.count = tempCount;
        }
        return count;
    }
}
