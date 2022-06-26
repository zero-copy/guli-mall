package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
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

    
    public static final String ID = "id";

    public static final String ORDER_ID = "order_id";

    public static final String ORDER_SN = "order_sn";

    public static final String SPU_ID = "spu_id";

    public static final String SPU_NAME = "spu_name";

    public static final String SPU_PIC = "spu_pic";

    public static final String SPU_BRAND = "spu_brand";

    public static final String CATEGORY_ID = "category_id";

    public static final String SKU_ID = "sku_id";

    public static final String SKU_NAME = "sku_name";

    public static final String SKU_PIC = "sku_pic";

    public static final String SKU_PRICE = "sku_price";

    public static final String SKU_QUANTITY = "sku_quantity";

    public static final String SKU_ATTRS_VALS = "sku_attrs_vals";

    public static final String PROMOTION_AMOUNT = "promotion_amount";

    public static final String COUPON_AMOUNT = "coupon_amount";

    public static final String INTEGRATION_AMOUNT = "integration_amount";

    public static final String REAL_AMOUNT = "real_amount";

    public static final String GIFT_INTEGRATION = "gift_integration";

    public static final String GIFT_GROWTH = "gift_growth";

    
}
