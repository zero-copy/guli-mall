package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 商品库存
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@Data
@TableName("wms_ware_sku")
public class WareSkuEntity implements Serializable {

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
    * 仓库id
    */
    private Long wareId;

    /**
    * 库存数
    */
    private Integer stock;

    /**
    * sku_name
    */
    private String skuName;

    /**
    * 锁定库存
    */
    private Integer stockLocked;

    
    private static final String ID = "id";

    private static final String SKU_ID = "sku_id";

    private static final String WARE_ID = "ware_id";

    private static final String STOCK = "stock";

    private static final String SKU_NAME = "sku_name";

    private static final String STOCK_LOCKED = "stock_locked";

    
}
