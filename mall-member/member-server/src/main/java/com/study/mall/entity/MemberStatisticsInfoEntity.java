package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
        import java.math.BigDecimal;
    
/**
 * 会员统计信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@Data
@TableName("ums_member_statistics_info")
public class MemberStatisticsInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 会员id
    */
    private Long memberId;

    /**
    * 累计消费金额
    */
    private BigDecimal consumeAmount;

    /**
    * 累计优惠金额
    */
    private BigDecimal couponAmount;

    /**
    * 订单数量
    */
    private Integer orderCount;

    /**
    * 优惠券数量
    */
    private Integer couponCount;

    /**
    * 评价数
    */
    private Integer commentCount;

    /**
    * 退货数量
    */
    private Integer returnOrderCount;

    /**
    * 登录次数
    */
    private Integer loginCount;

    /**
    * 关注数量
    */
    private Integer attendCount;

    /**
    * 粉丝数量
    */
    private Integer fansCount;

    /**
    * 收藏的商品数量
    */
    private Integer collectProductCount;

    /**
    * 收藏的专题活动数量
    */
    private Integer collectSubjectCount;

    /**
    * 收藏的评论数量
    */
    private Integer collectCommentCount;

    /**
    * 邀请的朋友数量
    */
    private Integer inviteFriendCount;

    
    private static final String ID = "id";

    private static final String MEMBER_ID = "member_id";

    private static final String CONSUME_AMOUNT = "consume_amount";

    private static final String COUPON_AMOUNT = "coupon_amount";

    private static final String ORDER_COUNT = "order_count";

    private static final String COUPON_COUNT = "coupon_count";

    private static final String COMMENT_COUNT = "comment_count";

    private static final String RETURN_ORDER_COUNT = "return_order_count";

    private static final String LOGIN_COUNT = "login_count";

    private static final String ATTEND_COUNT = "attend_count";

    private static final String FANS_COUNT = "fans_count";

    private static final String COLLECT_PRODUCT_COUNT = "collect_product_count";

    private static final String COLLECT_SUBJECT_COUNT = "collect_subject_count";

    private static final String COLLECT_COMMENT_COUNT = "collect_comment_count";

    private static final String INVITE_FRIEND_COUNT = "invite_friend_count";

    
}
