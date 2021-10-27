package com.study.mall.product.vo;

import com.study.mall.product.entity.AttrEntity;
import com.study.mall.product.entity.AttrGroupEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 10 28 上午 12:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AttrGroupWithAttrVo extends AttrGroupEntity implements Serializable {

    /**
     * 属性
     */
    private List<AttrEntity> attrs;
}
