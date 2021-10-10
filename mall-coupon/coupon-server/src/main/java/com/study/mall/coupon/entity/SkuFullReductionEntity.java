package com.study.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 商品满减信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Data
@TableName("sms_sku_full_reduction")
public class SkuFullReductionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * spu_id
    */
    private Long skuId;

    /**
    * 满多少
    */
    private BigDecimal fullPrice;

    /**
    * 减多少
    */
    private BigDecimal reducePrice;

    /**
    * 是否参与其他优惠
    */
    private Integer addOther;

    
    private static final String ID = "id";

    private static final String SKU_ID = "sku_id";

    private static final String FULL_PRICE = "full_price";

    private static final String REDUCE_PRICE = "reduce_price";

    private static final String ADD_OTHER = "add_other";

    
}
