package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
    import lombok.Data;

/**
 * 商品评价回复关系
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Data
@TableName("pms_comment_replay")
public class CommentReplayEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 评论id
    */
    private Long commentId;

    /**
    * 回复id
    */
    private Long replyId;

        
    private static final String ID = "id";

    private static final String COMMENT_ID = "comment_id";

    private static final String REPLY_ID = "reply_id";

    
}
