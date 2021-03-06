package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
    
/**
 * 库存工作单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@Data
@TableName("wms_ware_order_task")
public class WareOrderTaskEntity implements Serializable {

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
    * 收货人
    */
    private String consignee;

    /**
    * 收货人电话
    */
    private String consigneeTel;

    /**
    * 配送地址
    */
    private String deliveryAddress;

    /**
    * 订单备注
    */
    private String orderComment;

    /**
    * 付款方式【 1:在线付款 2:货到付款】
    */
    private Integer paymentWay;

    /**
    * 任务状态
    */
    private Integer taskStatus;

    /**
    * 订单描述
    */
    private String orderBody;

    /**
    * 物流单号
    */
    private String trackingNo;

    /**
    * create_time
    */
    private LocalDateTime createTime;

    /**
    * 仓库id
    */
    private Long wareId;

    /**
    * 工作单备注
    */
    private String taskComment;

    
    public static final String ID = "id";

    public static final String ORDER_ID = "order_id";

    public static final String ORDER_SN = "order_sn";

    public static final String CONSIGNEE = "consignee";

    public static final String CONSIGNEE_TEL = "consignee_tel";

    public static final String DELIVERY_ADDRESS = "delivery_address";

    public static final String ORDER_COMMENT = "order_comment";

    public static final String PAYMENT_WAY = "payment_way";

    public static final String TASK_STATUS = "task_status";

    public static final String ORDER_BODY = "order_body";

    public static final String TRACKING_NO = "tracking_no";

    public static final String CREATE_TIME = "create_time";

    public static final String WARE_ID = "ware_id";

    public static final String TASK_COMMENT = "task_comment";

    
}
