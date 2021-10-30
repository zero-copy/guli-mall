package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 会员登录记录
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@Data
@TableName("ums_member_login_log")
public class MemberLoginLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * member_id
    */
    private Long memberId;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * ip
    */
    private String ip;

    /**
    * city
    */
    private String city;

    /**
    * 登录类型[1-web，2-app]
    */
    private Integer loginType;

    
    private static final String ID = "id";

    private static final String MEMBER_ID = "member_id";

    private static final String CREATE_TIME = "create_time";

    private static final String IP = "ip";

    private static final String CITY = "city";

    private static final String LOGIN_TYPE = "login_type";

    
}
