package com.study.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 支付信息表
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@Data
@TableName("oms_payment_info")
public class PaymentInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 订单号（对外业务号）
    */
    private String orderSn;

    /**
    * 订单id
    */
    private Long orderId;

    /**
    * 支付宝交易流水号
    */
    private String alipayTradeNo;

    /**
    * 支付总金额
    */
    private BigDecimal totalAmount;

    /**
    * 交易内容
    */
    private String subject;

    /**
    * 支付状态
    */
    private String paymentStatus;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 确认时间
    */
    private LocalDateTime confirmTime;

    /**
    * 回调内容
    */
    private String callbackContent;

    /**
    * 回调时间
    */
    private LocalDateTime callbackTime;

    
    private static final String ID = "id";

    private static final String ORDER_SN = "order_sn";

    private static final String ORDER_ID = "order_id";

    private static final String ALIPAY_TRADE_NO = "alipay_trade_no";

    private static final String TOTAL_AMOUNT = "total_amount";

    private static final String SUBJECT = "subject";

    private static final String PAYMENT_STATUS = "payment_status";

    private static final String CREATE_TIME = "create_time";

    private static final String CONFIRM_TIME = "confirm_time";

    private static final String CALLBACK_CONTENT = "callback_content";

    private static final String CALLBACK_TIME = "callback_time";

    
}
