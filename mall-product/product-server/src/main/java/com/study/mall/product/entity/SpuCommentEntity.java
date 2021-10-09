package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品评价
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Data
@TableName("pms_spu_comment")
public class SpuCommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * sku_id
    */
    private Long skuId;

    /**
    * spu_id
    */
    private Long spuId;

    /**
    * 商品名字
    */
    private String spuName;

    /**
    * 会员昵称
    */
    private String memberNickName;

    /**
    * 星级
    */
    private Integer star;

    /**
    * 会员ip
    */
    private String memberIp;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 显示状态[0-不显示，1-显示]
    */
    private Integer showStatus;

    /**
    * 购买时属性组合
    */
    private String spuAttributes;

    /**
    * 点赞数
    */
    private Integer likesCount;

    /**
    * 回复数
    */
    private Integer replyCount;

    /**
    * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
    */
    private String resources;

    /**
    * 内容
    */
    private String content;

    /**
    * 用户头像
    */
    private String memberIcon;

    /**
    * 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
    */
    private Integer commentType;

        
    private static final String ID = "id";

    private static final String SKU_ID = "sku_id";

    private static final String SPU_ID = "spu_id";

    private static final String SPU_NAME = "spu_name";

    private static final String MEMBER_NICK_NAME = "member_nick_name";

    private static final String STAR = "star";

    private static final String MEMBER_IP = "member_ip";

    private static final String CREATE_TIME = "create_time";

    private static final String SHOW_STATUS = "show_status";

    private static final String SPU_ATTRIBUTES = "spu_attributes";

    private static final String LIKES_COUNT = "likes_count";

    private static final String REPLY_COUNT = "reply_count";

    private static final String RESOURCES = "resources";

    private static final String CONTENT = "content";

    private static final String MEMBER_ICON = "member_icon";

    private static final String COMMENT_TYPE = "comment_type";

    
}
