package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 退货原因
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@Data
@TableName("oms_order_return_reason")
public class OrderReturnReasonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 退货原因名
    */
    private String name;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 启用状态
    */
    private Integer status;

    /**
    * create_time
    */
    private LocalDateTime createTime;

    
    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String SORT = "sort";

    private static final String STATUS = "status";

    private static final String CREATE_TIME = "create_time";

    
}
