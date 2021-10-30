package com.study.mall.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Harlan
 * @date 2021 10 24 下午 06:02
 */
@Data
public class AttrGroupRelationReqVo implements Serializable {

    /**
     * 属性ID
     */
    private Long attrId;

    /**
     * 分组ID
     */
    private Long attrGroupId;
}
