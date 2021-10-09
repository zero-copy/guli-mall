package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

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

        
    private static final String ATTR_GROUP_ID = "attr_group_id";

    private static final String ATTR_GROUP_NAME = "attr_group_name";

    private static final String SORT = "sort";

    private static final String DESCRIPT = "descript";

    private static final String ICON = "icon";

    private static final String CATELOG_ID = "catelog_id";

    
}
