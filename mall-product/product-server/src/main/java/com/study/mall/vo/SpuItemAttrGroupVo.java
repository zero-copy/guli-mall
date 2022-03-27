package com.study.mall.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2022 03 27 下午 07:32
 */
@Data
public class SpuItemAttrGroupVo implements Serializable {

    private String groupName;

    private List<SpuBaseAttrVo> attrs;
}
