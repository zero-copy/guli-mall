package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
    
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


    public static final String ID = "id";

    public static final String LEVEL_ID = "level_id";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String NICKNAME = "nickname";

    public static final String MOBILE = "mobile";

    public static final String EMAIL = "email";

    public static final String HEADER = "header";

    public static final String GENDER = "gender";

    public static final String BIRTH = "birth";

    public static final String CITY = "city";

    public static final String JOB = "job";

    public static final String SIGN = "sign";

    public static final String SOURCE_TYPE = "source_type";

    public static final String INTEGRATION = "integration";

    public static final String GROWTH = "growth";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String SOCIAL_UID = "social_uid";

    public static final String ACCESS_TOKEN = "access_token";

    public static final String EXPIRES_IN = "expires_in";

    
}
