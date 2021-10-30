package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 库存工作单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@Data
@TableName("wms_ware_order_task_detail")
public class WareOrderTaskDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * sku_id
    */
    private Long skuId;

    /**
    * sku_name
    */
    private String skuName;

    /**
    * 购买个数
    */
    private Integer skuNum;

    /**
    * 工作单id
    */
    private Long taskId;

    /**
    * 仓库id
    */
    private Long wareId;

    /**
    * 1-已锁定  2-已解锁  3-扣减
    */
    private Integer lockStatus;

    
    private static final String ID = "id";

    private static final String SKU_ID = "sku_id";

    private static final String SKU_NAME = "sku_name";

    private static final String SKU_NUM = "sku_num";

    private static final String TASK_ID = "task_id";

    private static final String WARE_ID = "ware_id";

    private static final String LOCK_STATUS = "lock_status";

    
}
