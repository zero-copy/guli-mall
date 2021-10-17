package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 属性分组
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 分组id
    */
    @TableId
    private Long attrGroupId;

    /**
    * 组名
    */
    private String attrGroupName;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 描述
    */
    private String descript;

    /**
    * 组图标
    */
    private String icon;

    /**
    * 所属分类id
    */
    private Long catelogId;

    /**
     * 完整分类路径
     */
    @TableField(exist = false)
    private Long[] catelogPath;
        
    public static final String ATTR_GROUP_ID = "attr_group_id";

    public static final String ATTR_GROUP_NAME = "attr_group_name";

    public static final String SORT = "sort";

    public static final String DESCRIPT = "descript";

    public static final String ICON = "icon";

    public static final String CATELOG_ID = "catelog_id";

    
}
