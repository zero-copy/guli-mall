package com.study.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.study.mall.common.lang.R;
import com.study.mall.common.lang.dto.SkuInfoDto;
import com.study.mall.dto.SecKillSkuRedisDto;
import com.study.mall.dto.SeckillSessionDto;
import com.study.mall.feign.ISecKillSessionFeignService;
import com.study.mall.feign.ISkuInfoFeignService;
import com.study.mall.service.ISecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/26 20:26
 */
@Slf4j
@Service
public class SecKillServiceImpl implements ISecKillService {

    private static final String SEC_SESSION_CACHE_PREFIX = "seckill:session:";

    private static final String SKU_KILL_CACHE_PREFIX = "seckill:skus:";

    @Resource
    private ISecKillSessionFeignService secKillSessionFeignService;

    @Resource
    private ISkuInfoFeignService skuInfoFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;
    
    @Override
    public void uploadSecKillSku() {
        R<List<SeckillSessionDto>> sessionRes = secKillSessionFeignService.getLastThreeDaySession();
        if (sessionRes.getCode() == 0) {
            List<SeckillSessionDto> sessions = sessionRes.getData();
            //缓存到redis
            sessions.forEach(session -> {
                saveSession(session);
                saveSku(session);
            });
        }
    }

    private void saveSession(SeckillSessionDto session) {
        long startTime = session.getStartTime().toInstant(ZoneOffset.of("+8")).getEpochSecond();
        long endTime = session.getEndTime().toInstant(ZoneOffset.of("+8")).getEpochSecond();
        String key = SEC_SESSION_CACHE_PREFIX + startTime + "_" + endTime;
        List<String> skuRelationIds = session.getRelationSkus().stream().map(relation -> relation.getSkuId().toString()).collect(Collectors.toList());
        redisTemplate.opsForList().leftPushAll(key, skuRelationIds);
    }

    private void saveSku(SeckillSessionDto session) {
        BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SKU_KILL_CACHE_PREFIX);
        session.getRelationSkus().forEach(sku -> {
            SecKillSkuRedisDto redisDto = BeanUtil.copyProperties(sku, SecKillSkuRedisDto.class);
            R<SkuInfoDto> skuInfoRes = skuInfoFeignService.info(sku.getSkuId());
            if (skuInfoRes.getCode() == 0) {
                SkuInfoDto data = skuInfoRes.getData();
                redisDto.setSkuInfo(data);
            }
            ops.put(sku.getSkuId(), JSON.toJSONString(redisDto));
        });
    }
}
