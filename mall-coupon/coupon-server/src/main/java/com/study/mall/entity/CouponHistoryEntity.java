package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 优惠券领取历史记录
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@Data
@TableName("sms_coupon_history")
public class CouponHistoryEntity implements Serializable {

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
    * 会员id
    */
    private Long memberId;

    /**
    * 会员名字
    */
    private String memberNickName;

    /**
    * 获取方式[0->后台赠送；1->主动领取]
    */
    private Integer getType;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 使用状态[0->未使用；1->已使用；2->已过期]
    */
    private Integer useType;

    /**
    * 使用时间
    */
    private LocalDateTime useTime;

    /**
    * 订单id
    */
    private Long orderId;

    /**
    * 订单号
    */
    private Long orderSn;

    
    private static final String ID = "id";

    private static final String COUPON_ID = "coupon_id";

    private static final String MEMBER_ID = "member_id";

    private static final String MEMBER_NICK_NAME = "member_nick_name";

    private static final String GET_TYPE = "get_type";

    private static final String CREATE_TIME = "create_time";

    private static final String USE_TYPE = "use_type";

    private static final String USE_TIME = "use_time";

    private static final String ORDER_ID = "order_id";

    private static final String ORDER_SN = "order_sn";

    
}
