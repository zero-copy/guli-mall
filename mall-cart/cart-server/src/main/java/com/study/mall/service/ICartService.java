package com.study.mall.service;

import com.study.mall.entity.CartItemEntity;

import java.util.concurrent.ExecutionException;

/**
 * @author Harlan
 * @date 2022 06 17 下午 12:17
 */
public interface ICartService {

    /**
     * 添加商品到购物车
     * @param skuId 商品id
     * @param num 数量
     * @return 购物项
     */
    CartItemEntity addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    /**
     * 通过SkuId查询购物项
     * @param skuId skuId
     * @return 购物项
     */
    CartItemEntity getCartItem(Long skuId);
}
