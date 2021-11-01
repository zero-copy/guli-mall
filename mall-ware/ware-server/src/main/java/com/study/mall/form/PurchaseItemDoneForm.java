package com.study.mall.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2021 10 31 下午 09:27
 */
@Data
public class PurchaseItemDoneForm implements Serializable {

    private Long itemId;

    private Integer status;

    private String reason;
}
