package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 商品会员价格
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Data
@TableName("sms_member_price")
public class MemberPriceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * sku_id
    */
    private Long skuId;

    /**
    * 会员等级id
    */
    private Long memberLevelId;

    /**
    * 会员等级名
    */
    private String memberLevelName;

    /**
    * 会员对应价格
    */
    private BigDecimal memberPrice;

    /**
    * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
    */
    private Integer addOther;

    
    private static final String ID = "id";

    private static final String SKU_ID = "sku_id";

    private static final String MEMBER_LEVEL_ID = "member_level_id";

    private static final String MEMBER_LEVEL_NAME = "member_level_name";

    private static final String MEMBER_PRICE = "member_price";

    private static final String ADD_OTHER = "add_other";

    
}
