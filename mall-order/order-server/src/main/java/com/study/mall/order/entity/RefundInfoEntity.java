package com.study.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 退款信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@Data
@TableName("oms_refund_info")
public class RefundInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 退款的订单
    */
    private Long orderReturnId;

    /**
    * 退款金额
    */
    private BigDecimal refund;

    /**
    * 退款交易流水号
    */
    private String refundSn;

    /**
    * 退款状态
    */
    private Integer refundStatus;

    /**
    * 退款渠道[1-支付宝，2-微信，3-银联，4-汇款]
    */
    private Integer refundChannel;

    /**
    * 
    */
    private String refundContent;

    
    private static final String ID = "id";

    private static final String ORDER_RETURN_ID = "order_return_id";

    private static final String REFUND = "refund";

    private static final String REFUND_SN = "refund_sn";

    private static final String REFUND_STATUS = "refund_status";

    private static final String REFUND_CHANNEL = "refund_channel";

    private static final String REFUND_CONTENT = "refund_content";

    
}
