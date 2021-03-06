package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * sku图片
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_sku_images")
public class SkuImagesEntity implements Serializable {

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
    * 图片地址
    */
    private String imgUrl;

    /**
    * 排序
    */
    private Integer imgSort;

    /**
    * 默认图[0 - 不是默认图，1 - 是默认图]
    */
    private Integer defaultImg;

        
    public static final String ID = "id";

    public static final String SKU_ID = "sku_id";

    public static final String IMG_URL = "img_url";

    public static final String IMG_SORT = "img_sort";

    public static final String DEFAULT_IMG = "default_img";

    
}
