package com.study.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@Data
@TableName("mq_message")
public class MqMessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @TableId
    private String messageId;

    /**
    * 
    */
    private String content;

    /**
    * 
    */
    private String toExchane;

    /**
    * 
    */
    private String routingKey;

    /**
    * 
    */
    private String classType;

    /**
    * 0-新建 1-已发送 2-错误抵达 3-已抵达
    */
    private Integer messageStatus;

    /**
    * 
    */
    private LocalDateTime createTime;

    /**
    * 
    */
    private LocalDateTime updateTime;

    
    private static final String MESSAGE_ID = "message_id";

    private static final String CONTENT = "content";

    private static final String TO_EXCHANE = "to_exchane";

    private static final String ROUTING_KEY = "routing_key";

    private static final String CLASS_TYPE = "class_type";

    private static final String MESSAGE_STATUS = "message_status";

    private static final String CREATE_TIME = "create_time";

    private static final String UPDATE_TIME = "update_time";

    
}
