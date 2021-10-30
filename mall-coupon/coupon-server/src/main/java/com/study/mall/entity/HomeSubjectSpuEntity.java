package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 专题商品
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Data
@TableName("sms_home_subject_spu")
public class HomeSubjectSpuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 专题名字
    */
    private String name;

    /**
    * 专题id
    */
    private Long subjectId;

    /**
    * spu_id
    */
    private Long spuId;

    /**
    * 排序
    */
    private Integer sort;

    
    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String SUBJECT_ID = "subject_id";

    private static final String SPU_ID = "spu_id";

    private static final String SORT = "sort";

    
}
