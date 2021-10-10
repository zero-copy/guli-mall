package com.study.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 订单项信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@Data
@TableName("oms_order_item")
public class OrderItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * order_id
    */
    private Long orderId;

    /**
    * order_sn
    */
    private String orderSn;

    /**
    * spu_id
    */
    private Long spuId;

    /**
    * spu_name
    */
    private String spuName;

    /**
    * spu_pic
    */
    private String spuPic;

    /**
    * 品牌
    */
    private String spuBrand;

    /**
    * 商品分类id
    */
    private Long categoryId;

    /**
    * 商品sku编号
    */
    private Long skuId;

    /**
    * 商品sku名字
    */
    private String skuName;

    /**
    * 商品sku图片
    */
    private String skuPic;

    /**
    * 商品sku价格
    */
    private BigDecimal skuPrice;

    /**
    * 商品购买的数量
    */
    private Integer skuQuantity;

    /**
    * 商品销售属性组合（JSON）
    */
    private String skuAttrsVals;

    /**
    * 商品促销分解金额
    */
    private BigDecimal promotionAmount;

    /**
    * 优惠券优惠分解金额
    */
    private BigDecimal couponAmount;

    /**
    * 积分优惠分解金额
    */
    private BigDecimal integrationAmount;

    /**
    * 该商品经过优惠后的分解金额
    */
    private BigDecimal realAmount;

    /**
    * 赠送积分
    */
    private Integer giftIntegration;

    /**
    * 赠送成长值
    */
    private Integer giftGrowth;

    
    private static final String ID = "id";

    private static final String ORDER_ID = "order_id";

    private static final String ORDER_SN = "order_sn";

    private static final String SPU_ID = "spu_id";

    private static final String SPU_NAME = "spu_name";

    private static final String SPU_PIC = "spu_pic";

    private static final String SPU_BRAND = "spu_brand";

    private static final String CATEGORY_ID = "category_id";

    private static final String SKU_ID = "sku_id";

    private static final String SKU_NAME = "sku_name";

    private static final String SKU_PIC = "sku_pic";

    private static final String SKU_PRICE = "sku_price";

    private static final String SKU_QUANTITY = "sku_quantity";

    private static final String SKU_ATTRS_VALS = "sku_attrs_vals";

    private static final String PROMOTION_AMOUNT = "promotion_amount";

    private static final String COUPON_AMOUNT = "coupon_amount";

    private static final String INTEGRATION_AMOUNT = "integration_amount";

    private static final String REAL_AMOUNT = "real_amount";

    private static final String GIFT_INTEGRATION = "gift_integration";

    private static final String GIFT_GROWTH = "gift_growth";

    
}
