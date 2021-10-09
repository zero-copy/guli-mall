package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

/**
 * 属性&属性分组关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 属性id
    */
    private Long attrId;

    /**
    * 属性分组id
    */
    private Long attrGroupId;

    /**
    * 属性组内排序
    */
    private Integer attrSort;

        
    private static final String ID = "id";

    private static final String ATTR_ID = "attr_id";

    private static final String ATTR_GROUP_ID = "attr_group_id";

    private static final String ATTR_SORT = "attr_sort";

    
}
