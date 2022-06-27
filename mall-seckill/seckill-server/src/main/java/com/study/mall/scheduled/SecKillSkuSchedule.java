package com.study.mall.scheduled;

import com.study.mall.service.ISecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

    @Scheduled(cron = "0 0 3 * * ?")
    public void uploadSecKillSkuLastThreeDay() {
        secKillService.uploadSecKillSku();
    }
}
