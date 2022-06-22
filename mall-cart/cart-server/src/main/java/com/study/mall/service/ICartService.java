package com.study.mall.service;

import com.study.mall.entity.CartEntity;
import com.study.mall.entity.CartItemEntity;

import java.util.List;
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

    /**
     * 获取购物车
     * @return 购物车信息
     */
    CartEntity getCart();

    /**
     * 清空购物车
     * @param cartKey key
     */
    void clearCart(String cartKey);

    /**
     * 勾选购物项
     * @param skuId 商品id
     * @param check 是否勾选
     */
    void checkItem(Long skuId, Integer check);

    /**
     * 修改商品数量
     * @param skuId 商品Id
     * @param num 商品数量
     */
    void changeItemCount(Long skuId, Integer num);

    /**
     * 删除购物项
     * @param skuId 商品Id
     */
    void delItem(Long skuId);

    List<CartItemEntity> getUserCartItems();
}
