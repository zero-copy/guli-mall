package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 会员收藏的商品
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@Data
@TableName("ums_member_collect_spu")
public class MemberCollectSpuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 会员id
    */
    private Long memberId;

    /**
    * spu_id
    */
    private Long spuId;

    /**
    * spu_name
    */
    private String spuName;

    /**
    * spu_img
    */
    private String spuImg;

    /**
    * create_time
    */
    private LocalDateTime createTime;

    
    private static final String ID = "id";

    private static final String MEMBER_ID = "member_id";

    private static final String SPU_ID = "spu_id";

    private static final String SPU_NAME = "spu_name";

    private static final String SPU_IMG = "spu_img";

    private static final String CREATE_TIME = "create_time";

    
}
