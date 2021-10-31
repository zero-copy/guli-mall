package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
    
/**
 * 采购信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@Data
@TableName("wms_purchase")
public class PurchaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @TableId
    private Long id;

    /**
    * 
    */
    private Long assigneeId;

    /**
    * 
    */
    private String assigneeName;

    /**
    * 
    */
    private String phone;

    /**
    * 
    */
    private Integer priority;

    /**
    * 
    */
    private Integer status;

    /**
    * 
    */
    private Long wareId;

    /**
    * 
    */
    private BigDecimal amount;

    /**
    * 
    */
    private LocalDateTime createTime;

    /**
    * 
    */
    private LocalDateTime updateTime;

    
    public static final String ID = "id";

    public static final String ASSIGNEE_ID = "assignee_id";

    public static final String ASSIGNEE_NAME = "assignee_name";

    public static final String PHONE = "phone";

    public static final String PRIORITY = "priority";

    public static final String STATUS = "status";

    public static final String WARE_ID = "ware_id";

    public static final String AMOUNT = "amount";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    
}
