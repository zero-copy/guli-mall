package com.study.mall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 10 31 下午 09:26
 */
@Data
public class PurchaseDoneForm implements Serializable {

    @NotNull
    private Long id;

    private List<PurchaseItemDoneForm> items;
}
