package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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
    @TableId(type = IdType.INPUT)
    private Long spuId;

    /**
    * 商品介绍
    */
    private String decript;

        
    private static final String SPU_ID = "spu_id";

    private static final String DECRIPT = "decript";

    
}
