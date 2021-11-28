package com.study.mall.constant;

/**
 * @author Harlan
 * @date 2021 11 03 上午 01:35
 */
public class EsConstant {

    private EsConstant() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 商品索引
     */
    public static final String PRODUCT_INDEX = "product";

    /**
     * 查询每页大小
     */
    public static final Integer PRODUCT_PAGE_SIZE = 2;
}
