package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

    @TableField(exist = false)
    private List<SeckillSkuRelationEntity> relationSkus;

    
    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    
}
