package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 优惠券信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@Data
@TableName("sms_coupon")
public class CouponEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
    */
    private Integer couponType;

    /**
    * 优惠券图片
    */
    private String couponImg;

    /**
    * 优惠卷名字
    */
    private String couponName;

    /**
    * 数量
    */
    private Integer num;

    /**
    * 金额
    */
    private BigDecimal amount;

    /**
    * 每人限领张数
    */
    private Integer perLimit;

    /**
    * 使用门槛
    */
    private BigDecimal minPoint;

    /**
    * 开始时间
    */
    private LocalDateTime startTime;

    /**
    * 结束时间
    */
    private LocalDateTime endTime;

    /**
    * 使用类型[0->全场通用；1->指定分类；2->指定商品]
    */
    private Integer useType;

    /**
    * 备注
    */
    private String note;

    /**
    * 发行数量
    */
    private Integer publishCount;

    /**
    * 已使用数量
    */
    private Integer useCount;

    /**
    * 领取数量
    */
    private Integer receiveCount;

    /**
    * 可以领取的开始日期
    */
    private LocalDateTime enableStartTime;

    /**
    * 可以领取的结束日期
    */
    private LocalDateTime enableEndTime;

    /**
    * 优惠码
    */
    private String code;

    /**
    * 可以领取的会员等级[0->不限等级，其他-对应等级]
    */
    private Integer memberLevel;

    /**
    * 发布状态[0-未发布，1-已发布]
    */
    private Integer publish;

    
    private static final String ID = "id";

    private static final String COUPON_TYPE = "coupon_type";

    private static final String COUPON_IMG = "coupon_img";

    private static final String COUPON_NAME = "coupon_name";

    private static final String NUM = "num";

    private static final String AMOUNT = "amount";

    private static final String PER_LIMIT = "per_limit";

    private static final String MIN_POINT = "min_point";

    private static final String START_TIME = "start_time";

    private static final String END_TIME = "end_time";

    private static final String USE_TYPE = "use_type";

    private static final String NOTE = "note";

    private static final String PUBLISH_COUNT = "publish_count";

    private static final String USE_COUNT = "use_count";

    private static final String RECEIVE_COUNT = "receive_count";

    private static final String ENABLE_START_TIME = "enable_start_time";

    private static final String ENABLE_END_TIME = "enable_end_time";

    private static final String CODE = "code";

    private static final String MEMBER_LEVEL = "member_level";

    private static final String PUBLISH = "publish";

    
}
