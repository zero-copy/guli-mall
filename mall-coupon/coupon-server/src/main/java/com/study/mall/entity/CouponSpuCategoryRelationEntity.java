package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 优惠券分类关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@Data
@TableName("sms_coupon_spu_category_relation")
public class CouponSpuCategoryRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 优惠券id
    */
    private Long couponId;

    /**
    * 产品分类id
    */
    private Long categoryId;

    /**
    * 产品分类名称
    */
    private String categoryName;

    
    private static final String ID = "id";

    private static final String COUPON_ID = "coupon_id";

    private static final String CATEGORY_ID = "category_id";

    private static final String CATEGORY_NAME = "category_name";

    
}
