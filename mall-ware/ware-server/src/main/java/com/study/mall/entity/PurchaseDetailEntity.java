package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
    
/**
 * 
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@Data
@TableName("wms_purchase_detail")
public class PurchaseDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @TableId
    private Long id;

    /**
    * 采购单id
    */
    private Long purchaseId;

    /**
    * 采购商品id
    */
    private Long skuId;

    /**
    * 采购数量
    */
    private Integer skuNum;

    /**
    * 采购金额
    */
    private BigDecimal skuPrice;

    /**
    * 仓库id
    */
    private Long wareId;

    /**
    * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
    */
    private Integer status;

    
    public static final String ID = "id";

    public static final String PURCHASE_ID = "purchase_id";

    public static final String SKU_ID = "sku_id";

    public static final String SKU_NUM = "sku_num";

    public static final String SKU_PRICE = "sku_price";

    public static final String WARE_ID = "ware_id";

    public static final String STATUS = "status";

    
}
