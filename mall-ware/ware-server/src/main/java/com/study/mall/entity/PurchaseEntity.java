package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
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

    
    private static final String ID = "id";

    private static final String ASSIGNEE_ID = "assignee_id";

    private static final String ASSIGNEE_NAME = "assignee_name";

    private static final String PHONE = "phone";

    private static final String PRIORITY = "priority";

    private static final String STATUS = "status";

    private static final String WARE_ID = "ware_id";

    private static final String AMOUNT = "amount";

    private static final String CREATE_TIME = "create_time";

    private static final String UPDATE_TIME = "update_time";

    
}
