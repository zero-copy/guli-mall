package com.study.mall.controller;

import com.study.mall.common.lang.R;
import com.study.mall.dto.SecKillSkuRedisDto;
import com.study.mall.service.ISecKillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/27 14:30
 */
@RestController
@RequestMapping("/seckill")
public class SecKillController {

    @Resource
    private ISecKillService secKillService;

    @GetMapping("/currentSeckillSkus")
    public R getCurrentSecKillSkus() {
        List<SecKillSkuRedisDto> vo = secKillService.getCurrentSecKillSkus();
        return R.ok(vo);
    }
}
