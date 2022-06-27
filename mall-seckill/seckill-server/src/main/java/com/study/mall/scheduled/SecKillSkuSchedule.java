package com.study.mall.scheduled;

import com.study.mall.service.ISecKillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/26 20:15
 */
@Slf4j
@Component
public class SecKillSkuSchedule {

    @Resource
    private ISecKillService secKillService;

    @Resource
    private RedissonClient redissonClient;

    private static final String UPLOAD_LOCK = "seckill:upload:lock";

    @Scheduled(cron = "0 0 3 * * ?")
    public void uploadSecKillSkuLastThreeDay() {
        log.info("====定时上架商品信息====");
        RLock lock = redissonClient.getLock(UPLOAD_LOCK);
        lock.lock(10, TimeUnit.SECONDS);
        try {
            secKillService.uploadSecKillSku();
        } finally {
            lock.unlock();
        }
        log.info("========上架完成=======");
    }
}
