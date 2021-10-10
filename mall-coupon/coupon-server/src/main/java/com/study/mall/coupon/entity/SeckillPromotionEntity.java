package com.study.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 秒杀活动
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Data
@TableName("sms_seckill_promotion")
public class SeckillPromotionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 活动标题
    */
    private String title;

    /**
    * 开始日期
    */
    private LocalDateTime startTime;

    /**
    * 结束日期
    */
    private LocalDateTime endTime;

    /**
    * 上下线状态
    */
    private Integer status;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 创建人
    */
    private Long userId;

    
    private static final String ID = "id";

    private static final String TITLE = "title";

    private static final String START_TIME = "start_time";

    private static final String END_TIME = "end_time";

    private static final String STATUS = "status";

    private static final String CREATE_TIME = "create_time";

    private static final String USER_ID = "user_id";

    
}
