package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
    
/**
 * 会员等级
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@Data
@TableName("ums_member_level")
public class MemberLevelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 等级名称
    */
    private String name;

    /**
    * 等级需要的成长值
    */
    private Integer growthPoint;

    /**
    * 是否为默认等级[0->不是；1->是]
    */
    private Integer defaultStatus;

    /**
    * 免运费标准
    */
    private BigDecimal freeFreightPoint;

    /**
    * 每次评价获取的成长值
    */
    private Integer commentGrowthPoint;

    /**
    * 是否有免邮特权
    */
    private Integer priviledgeFreeFreight;

    /**
    * 是否有会员价格特权
    */
    private Integer priviledgeMemberPrice;

    /**
    * 是否有生日特权
    */
    private Integer priviledgeBirthday;

    /**
    * 备注
    */
    private String note;

    
    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String GROWTH_POINT = "growth_point";

    public static final String DEFAULT_STATUS = "default_status";

    public static final String FREE_FREIGHT_POINT = "free_freight_point";

    public static final String COMMENT_GROWTH_POINT = "comment_growth_point";

    public static final String PRIVILEDGE_FREE_FREIGHT = "priviledge_free_freight";

    public static final String PRIVILEDGE_MEMBER_PRICE = "priviledge_member_price";

    public static final String PRIVILEDGE_BIRTHDAY = "priviledge_birthday";

    public static final String NOTE = "note";

    
}
