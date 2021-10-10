package com.study.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 积分变化历史记录
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:58
 */
@Data
@TableName("ums_integration_change_history")
public class IntegrationChangeHistoryEntity implements Serializable {

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
    * create_time
    */
    private LocalDateTime createTime;

    /**
    * 变化的值
    */
    private Integer changeCount;

    /**
    * 备注
    */
    private String note;

    /**
    * 来源[0->购物；1->管理员修改;2->活动]
    */
    private Integer sourceTyoe;

    
    private static final String ID = "id";

    private static final String MEMBER_ID = "member_id";

    private static final String CREATE_TIME = "create_time";

    private static final String CHANGE_COUNT = "change_count";

    private static final String NOTE = "note";

    private static final String SOURCE_TYOE = "source_tyoe";

    
}
