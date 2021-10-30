package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.*;
import java.io.Serializable;

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
    @NotBlank(message = "品牌名不能为空")
    private String name;

    /**
    * 品牌logo地址
    */
    @NotBlank(message = "logo地址不能为空")
    @URL(message = "logo必须为合法URL地址")
    private String logo;

    /**
    * 介绍
    */
    @NotBlank(message = "介绍不能为空")
    private String descript;

    /**
    * 显示状态[0-不显示；1-显示]
    */
    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private Integer showStatus;

    /**
    * 检索首字母
    */
    @NotBlank(message = "检索首字母不能为空")
    @Pattern(regexp = "[a-zA-Z]", message = "必须为字母")
    private String firstLetter;

    /**
    * 排序
    */
    @Min(value = 0, message = "必须大于等于0")
    private Integer sort;

        
    public static final String BRAND_ID = "brand_id";

    public static final String NAME = "name";

    public static final String LOGO = "logo";

    public static final String DESCRIPT = "descript";

    public static final String SHOW_STATUS = "show_status";

    public static final String FIRST_LETTER = "first_letter";

    public static final String SORT = "sort";

    
}
