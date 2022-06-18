package com.study.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.study.mall.common.dto.TempUserInfo;
import com.study.mall.common.lang.R;
import com.study.mall.common.lang.dto.SkuInfoDto;
import com.study.mall.entity.CartItemEntity;
import com.study.mall.feign.ISkuInfoFeignService;
import com.study.mall.feign.ISkuSaleAttrValueFeignService;
import com.study.mall.interceptor.CartInterceptor;
import com.study.mall.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Harlan
 * @date 2022 06 17 下午 12:17
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Resource
    ISkuInfoFeignService skuInfoFeignService;

    @Resource
    ISkuSaleAttrValueFeignService saleAttrValueFeignService;

    @Resource
    ThreadPoolExecutor threadPool;

    private static final String CART_PREFIX = "gulimall:cart:";
    @Override
    public CartItemEntity addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String dataStr = (String) cartOps.get(skuId.toString());
        CartItemEntity cartItem;
        if (StringUtils.isEmpty(dataStr)) {
            cartItem = new CartItemEntity();
            CompletableFuture<Void> getSkuInfoTask = CompletableFuture.runAsync(() -> {
                R<SkuInfoDto> res = skuInfoFeignService.info(skuId);
                if (res.getCode() == 0) {
                    SkuInfoDto skuInfo = res.getData();
                    cartItem.setImage(skuInfo.getSkuDefaultImg());
                    cartItem.setTitle(skuInfo.getSkuTitle());
                    cartItem.setPrice(skuInfo.getPrice());
                    cartItem.setSkuId(skuInfo.getSkuId());
                    cartItem.setChecked(true);
                    cartItem.setCount(num);
                }
            }, threadPool);
            CompletableFuture<Void> getAttrStringListTask = CompletableFuture.runAsync(() -> {
                R<List<String>> res = saleAttrValueFeignService.getSkuSaleAttrValues(skuId);
                if (res.getCode() == 0) {
                    List<String> attrStringList = res.getData();
                    cartItem.setSkuAttr(attrStringList);
                }
            }, threadPool);
            CompletableFuture.allOf(getSkuInfoTask, getAttrStringListTask).get();
        } else {
            cartItem = JSON.parseObject(dataStr, CartItemEntity.class);
            cartItem.setCount(cartItem.getCount() + num);
        }
        cartOps.put(skuId.toString(), JSON.toJSONString(cartItem));
        return cartItem;
    }

    @Override
    public CartItemEntity getCartItem(Long skuId) {
        return JSON.parseObject((String) getCartOps().get(skuId.toString()), CartItemEntity.class);
    }

    private BoundHashOperations<String, Object, Object> getCartOps() {
        TempUserInfo tempUserInfo = CartInterceptor.THREAD_LOCAL.get();
        String cartKey;
        if (tempUserInfo.getUserId() != null) {
            cartKey = CART_PREFIX + tempUserInfo.getUserId();
        } else {
            cartKey = CART_PREFIX + tempUserInfo.getUserKey();
        }
        return redisTemplate.boundHashOps(cartKey);
    }
}
