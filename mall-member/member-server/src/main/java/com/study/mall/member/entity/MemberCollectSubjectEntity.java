package com.study.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 会员收藏的专题活动
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@Data
@TableName("ums_member_collect_subject")
public class MemberCollectSubjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * subject_id
    */
    private Long subjectId;

    /**
    * subject_name
    */
    private String subjectName;

    /**
    * subject_img
    */
    private String subjectImg;

    /**
    * 活动url
    */
    private String subjectUrll;

    
    private static final String ID = "id";

    private static final String SUBJECT_ID = "subject_id";

    private static final String SUBJECT_NAME = "subject_name";

    private static final String SUBJECT_IMG = "subject_img";

    private static final String SUBJECT_URLL = "subject_urll";

    
}
