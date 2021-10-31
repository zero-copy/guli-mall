package com.study.mall.form;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 10 31 下午 06:35
 */
@Data
public class MergeForm implements Serializable {

    private Long purchaseId;

    private List<Long> items;
}
