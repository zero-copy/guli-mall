package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 会员
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:58
 */
@Data
@TableName("ums_member")
public class MemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 会员等级id
    */
    private Long levelId;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 手机号码
    */
    private String mobile;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 头像
    */
    private String header;

    /**
    * 性别
    */
    private Integer gender;

    /**
    * 生日
    */
    private LocalDate birth;

    /**
    * 所在城市
    */
    private String city;

    /**
    * 职业
    */
    private String job;

    /**
    * 个性签名
    */
    private String sign;

    /**
    * 用户来源
    */
    private Integer sourceType;

    /**
    * 积分
    */
    private Integer integration;

    /**
    * 成长值
    */
    private Integer growth;

    /**
    * 启用状态
    */
    private Integer status;

    /**
    * 注册时间
    */
    private LocalDateTime createTime;

    /**
    * 社交用户的唯一id
    */
    private String socialUid;

    /**
    * 访问令牌
    */
    private String accessToken;

    /**
    * 访问令牌的时间
    */
    private String expiresIn;

    
    private static final String ID = "id";

    private static final String LEVEL_ID = "level_id";

    private static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    private static final String NICKNAME = "nickname";

    private static final String MOBILE = "mobile";

    private static final String EMAIL = "email";

    private static final String HEADER = "header";

    private static final String GENDER = "gender";

    private static final String BIRTH = "birth";

    private static final String CITY = "city";

    private static final String JOB = "job";

    private static final String SIGN = "sign";

    private static final String SOURCE_TYPE = "source_type";

    private static final String INTEGRATION = "integration";

    private static final String GROWTH = "growth";

    private static final String STATUS = "status";

    private static final String CREATE_TIME = "create_time";

    private static final String SOCIAL_UID = "social_uid";

    private static final String ACCESS_TOKEN = "access_token";

    private static final String EXPIRES_IN = "expires_in";

    
}
