package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 订单配置信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@Data
@TableName("oms_order_setting")
public class OrderSettingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 秒杀订单超时关闭时间(分)
    */
    private Integer flashOrderOvertime;

    /**
    * 正常订单超时时间(分)
    */
    private Integer normalOrderOvertime;

    /**
    * 发货后自动确认收货时间（天）
    */
    private Integer confirmOvertime;

    /**
    * 自动完成交易时间，不能申请退货（天）
    */
    private Integer finishOvertime;

    /**
    * 订单完成后自动好评时间（天）
    */
    private Integer commentOvertime;

    /**
    * 会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】
    */
    private Integer memberLevel;

    
    private static final String ID = "id";

    private static final String FLASH_ORDER_OVERTIME = "flash_order_overtime";

    private static final String NORMAL_ORDER_OVERTIME = "normal_order_overtime";

    private static final String CONFIRM_OVERTIME = "confirm_overtime";

    private static final String FINISH_OVERTIME = "finish_overtime";

    private static final String COMMENT_OVERTIME = "comment_overtime";

    private static final String MEMBER_LEVEL = "member_level";

    
}
