package com.study.mall.controller;

import com.study.mall.common.lang.R;
import com.study.mall.scheduled.SecKillSkuSchedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/27 12:26
 */
@RestController
@RequestMapping("/seckill/scheduled")
public class ScheduledController {

    @Resource
    private SecKillSkuSchedule secKillSkuSchedule;


    @GetMapping("/secKillUpload")
    public R<Object> doSecKillSkuUpload() {
        secKillSkuSchedule.uploadSecKillSkuLastThreeDay();
        return R.ok();
    }
}
