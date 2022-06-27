package com.study.mall.service;

import com.study.mall.dto.SecKillSkuRedisDto;

import java.util.List;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/26 20:26
 */
public interface ISecKillService {

    /**
     * 上架秒杀商品
     */
    void uploadSecKillSku();

    List<SecKillSkuRedisDto> getCurrentSecKillSkus();
}
