package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

/**
 * 品牌
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 品牌id
    */
    @TableId
    private Long brandId;

    /**
    * 品牌名
    */
    private String name;

    /**
    * 品牌logo地址
    */
    private String logo;

    /**
    * 介绍
    */
    private String descript;

    /**
    * 显示状态[0-不显示；1-显示]
    */
    private Integer showStatus;

    /**
    * 检索首字母
    */
    private String firstLetter;

    /**
    * 排序
    */
    private Integer sort;

        
    private static final String BRAND_ID = "brand_id";

    private static final String NAME = "name";

    private static final String LOGO = "logo";

    private static final String DESCRIPT = "descript";

    private static final String SHOW_STATUS = "show_status";

    private static final String FIRST_LETTER = "first_letter";

    private static final String SORT = "sort";

    
}
