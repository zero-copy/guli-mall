package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 订单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@Data
@TableName("oms_order")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * member_id
    */
    private Long memberId;

    /**
    * 订单号
    */
    private String orderSn;

    /**
    * 使用的优惠券
    */
    private Long couponId;

    /**
    * create_time
    */
    private LocalDateTime createTime;

    /**
    * 用户名
    */
    private String memberUsername;

    /**
    * 订单总额
    */
    private BigDecimal totalAmount;

    /**
    * 应付总额
    */
    private BigDecimal payAmount;

    /**
    * 运费金额
    */
    private BigDecimal freightAmount;

    /**
    * 促销优化金额（促销价、满减、阶梯价）
    */
    private BigDecimal promotionAmount;

    /**
    * 积分抵扣金额
    */
    private BigDecimal integrationAmount;

    /**
    * 优惠券抵扣金额
    */
    private BigDecimal couponAmount;

    /**
    * 后台调整订单使用的折扣金额
    */
    private BigDecimal discountAmount;

    /**
    * 支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】
    */
    private Integer payType;

    /**
    * 订单来源[0->PC订单；1->app订单]
    */
    private Integer sourceType;

    /**
    * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
    */
    private Integer status;

    /**
    * 物流公司(配送方式)
    */
    private String deliveryCompany;

    /**
    * 物流单号
    */
    private String deliverySn;

    /**
    * 自动确认时间（天）
    */
    private Integer autoConfirmDay;

    /**
    * 可以获得的积分
    */
    private Integer integration;

    /**
    * 可以获得的成长值
    */
    private Integer growth;

    /**
    * 发票类型[0->不开发票；1->电子发票；2->纸质发票]
    */
    private Integer billType;

    /**
    * 发票抬头
    */
    private String billHeader;

    /**
    * 发票内容
    */
    private String billContent;

    /**
    * 收票人电话
    */
    private String billReceiverPhone;

    /**
    * 收票人邮箱
    */
    private String billReceiverEmail;

    /**
    * 收货人姓名
    */
    private String receiverName;

    /**
    * 收货人电话
    */
    private String receiverPhone;

    /**
    * 收货人邮编
    */
    private String receiverPostCode;

    /**
    * 省份/直辖市
    */
    private String receiverProvince;

    /**
    * 城市
    */
    private String receiverCity;

    /**
    * 区
    */
    private String receiverRegion;

    /**
    * 详细地址
    */
    private String receiverDetailAddress;

    /**
    * 订单备注
    */
    private String note;

    /**
    * 确认收货状态[0->未确认；1->已确认]
    */
    private Integer confirmStatus;

    /**
    * 删除状态【0->未删除；1->已删除】
    */
    private Integer deleteStatus;

    /**
    * 下单时使用的积分
    */
    private Integer useIntegration;

    /**
    * 支付时间
    */
    private LocalDateTime paymentTime;

    /**
    * 发货时间
    */
    private LocalDateTime deliveryTime;

    /**
    * 确认收货时间
    */
    private LocalDateTime receiveTime;

    /**
    * 评价时间
    */
    private LocalDateTime commentTime;

    /**
    * 修改时间
    */
    private LocalDateTime modifyTime;

    
    private static final String ID = "id";

    private static final String MEMBER_ID = "member_id";

    private static final String ORDER_SN = "order_sn";

    private static final String COUPON_ID = "coupon_id";

    private static final String CREATE_TIME = "create_time";

    private static final String MEMBER_USERNAME = "member_username";

    private static final String TOTAL_AMOUNT = "total_amount";

    private static final String PAY_AMOUNT = "pay_amount";

    private static final String FREIGHT_AMOUNT = "freight_amount";

    private static final String PROMOTION_AMOUNT = "promotion_amount";

    private static final String INTEGRATION_AMOUNT = "integration_amount";

    private static final String COUPON_AMOUNT = "coupon_amount";

    private static final String DISCOUNT_AMOUNT = "discount_amount";

    private static final String PAY_TYPE = "pay_type";

    private static final String SOURCE_TYPE = "source_type";

    private static final String STATUS = "status";

    private static final String DELIVERY_COMPANY = "delivery_company";

    private static final String DELIVERY_SN = "delivery_sn";

    private static final String AUTO_CONFIRM_DAY = "auto_confirm_day";

    private static final String INTEGRATION = "integration";

    private static final String GROWTH = "growth";

    private static final String BILL_TYPE = "bill_type";

    private static final String BILL_HEADER = "bill_header";

    private static final String BILL_CONTENT = "bill_content";

    private static final String BILL_RECEIVER_PHONE = "bill_receiver_phone";

    private static final String BILL_RECEIVER_EMAIL = "bill_receiver_email";

    private static final String RECEIVER_NAME = "receiver_name";

    private static final String RECEIVER_PHONE = "receiver_phone";

    private static final String RECEIVER_POST_CODE = "receiver_post_code";

    private static final String RECEIVER_PROVINCE = "receiver_province";

    private static final String RECEIVER_CITY = "receiver_city";

    private static final String RECEIVER_REGION = "receiver_region";

    private static final String RECEIVER_DETAIL_ADDRESS = "receiver_detail_address";

    private static final String NOTE = "note";

    private static final String CONFIRM_STATUS = "confirm_status";

    private static final String DELETE_STATUS = "delete_status";

    private static final String USE_INTEGRATION = "use_integration";

    private static final String PAYMENT_TIME = "payment_time";

    private static final String DELIVERY_TIME = "delivery_time";

    private static final String RECEIVE_TIME = "receive_time";

    private static final String COMMENT_TIME = "comment_time";

    private static final String MODIFY_TIME = "modify_time";

    
}
