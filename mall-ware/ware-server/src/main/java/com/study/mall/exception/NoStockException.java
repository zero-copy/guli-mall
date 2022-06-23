package com.study.mall.exception;

/**
 * @author harlan
 */
public class NoStockException extends RuntimeException {

    public NoStockException(Long skuId) {
        super("商品:" + skuId + "没有足够库存");
    }
}
