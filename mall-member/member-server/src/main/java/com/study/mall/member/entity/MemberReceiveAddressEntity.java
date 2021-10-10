package com.study.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 会员收货地址
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@Data
@TableName("ums_member_receive_address")
public class MemberReceiveAddressEntity implements Serializable {

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
    * 收货人姓名
    */
    private String name;

    /**
    * 电话
    */
    private String phone;

    /**
    * 邮政编码
    */
    private String postCode;

    /**
    * 省份/直辖市
    */
    private String province;

    /**
    * 城市
    */
    private String city;

    /**
    * 区
    */
    private String region;

    /**
    * 详细地址(街道)
    */
    private String detailAddress;

    /**
    * 省市区代码
    */
    private String areacode;

    /**
    * 是否默认
    */
    private Integer defaultStatus;

    
    private static final String ID = "id";

    private static final String MEMBER_ID = "member_id";

    private static final String NAME = "name";

    private static final String PHONE = "phone";

    private static final String POST_CODE = "post_code";

    private static final String PROVINCE = "province";

    private static final String CITY = "city";

    private static final String REGION = "region";

    private static final String DETAIL_ADDRESS = "detail_address";

    private static final String AREACODE = "areacode";

    private static final String DEFAULT_STATUS = "default_status";

    
}
