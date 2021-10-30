package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

/**
 * spu图片
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Data
@TableName("pms_spu_images")
public class SpuImagesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * spu_id
    */
    private Long spuId;

    /**
    * 图片名
    */
    private String imgName;

    /**
    * 图片地址
    */
    private String imgUrl;

    /**
    * 顺序
    */
    private Integer imgSort;

    /**
    * 是否默认图
    */
    private Integer defaultImg;

        
    private static final String ID = "id";

    private static final String SPU_ID = "spu_id";

    private static final String IMG_NAME = "img_name";

    private static final String IMG_URL = "img_url";

    private static final String IMG_SORT = "img_sort";

    private static final String DEFAULT_IMG = "default_img";

    
}
