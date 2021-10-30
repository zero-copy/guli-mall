package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 商品阶梯价格
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Data
@TableName("sms_sku_ladder")
public class SkuLadderEntity implements Serializable {

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
    * 满几件
    */
    private Integer fullCount;

    /**
    * 打几折
    */
    private BigDecimal discount;

    /**
    * 折后价
    */
    private BigDecimal price;

    /**
    * 是否叠加其他优惠[0-不可叠加，1-可叠加]
    */
    private Integer addOther;

    
    private static final String ID = "id";

    private static final String SKU_ID = "sku_id";

    private static final String FULL_COUNT = "full_count";

    private static final String DISCOUNT = "discount";

    private static final String PRICE = "price";

    private static final String ADD_OTHER = "add_other";

    
}
