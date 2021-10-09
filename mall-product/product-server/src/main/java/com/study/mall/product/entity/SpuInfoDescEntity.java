package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

/**
 * spu信息介绍
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 商品id
    */
    @TableId
    private Long spuId;

    /**
    * 商品介绍
    */
    private String decript;

        
    private static final String SPU_ID = "spu_id";

    private static final String DECRIPT = "decript";

    
}
