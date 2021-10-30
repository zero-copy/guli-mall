package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 首页轮播广告
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@Data
@TableName("sms_home_adv")
public class HomeAdvEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 名字
    */
    private String name;

    /**
    * 图片地址
    */
    private String pic;

    /**
    * 开始时间
    */
    private LocalDateTime startTime;

    /**
    * 结束时间
    */
    private LocalDateTime endTime;

    /**
    * 状态
    */
    private Integer status;

    /**
    * 点击数
    */
    private Integer clickCount;

    /**
    * 广告详情连接地址
    */
    private String url;

    /**
    * 备注
    */
    private String note;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 发布者
    */
    private Long publisherId;

    /**
    * 审核者
    */
    private Long authId;

    
    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String PIC = "pic";

    private static final String START_TIME = "start_time";

    private static final String END_TIME = "end_time";

    private static final String STATUS = "status";

    private static final String CLICK_COUNT = "click_count";

    private static final String URL = "url";

    private static final String NOTE = "note";

    private static final String SORT = "sort";

    private static final String PUBLISHER_ID = "publisher_id";

    private static final String AUTH_ID = "auth_id";

    
}
