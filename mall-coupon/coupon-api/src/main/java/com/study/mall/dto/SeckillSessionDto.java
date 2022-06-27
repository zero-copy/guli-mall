package com.study.mall.dto;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class SeckillSessionDto implements Serializable {

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

    private List<SeckillSkuRelationDto> relationSkus;
}
