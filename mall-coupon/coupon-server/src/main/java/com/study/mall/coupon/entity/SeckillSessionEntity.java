package com.study.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 秒杀活动场次
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Data
@TableName("sms_seckill_session")
public class SeckillSessionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 场次名称
    */
    private String name;

    /**
    * 每日开始时间
    */
    private LocalDateTime startTime;

    /**
    * 每日结束时间
    */
    private LocalDateTime endTime;

    /**
    * 启用状态
    */
    private Integer status;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    
    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String START_TIME = "start_time";

    private static final String END_TIME = "end_time";

    private static final String STATUS = "status";

    private static final String CREATE_TIME = "create_time";

    
}
