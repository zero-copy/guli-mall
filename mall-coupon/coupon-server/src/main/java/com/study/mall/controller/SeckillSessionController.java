package com.study.mall.controller;

import com.study.mall.common.lang.R;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.SeckillSessionEntity;
import com.study.mall.service.ISeckillSessionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 秒杀活动场次
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/seckillsession")
public class SeckillSessionController {

    @Resource
    private ISeckillSessionService seckillSessionService;

    @GetMapping("/lastSession")
    public R<List<SeckillSessionEntity>> getLastThreeDaySession() {
        List<SeckillSessionEntity> sessionEntities = seckillSessionService.getLastThreeDaySession();
        return R.ok(sessionEntities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:seckillsession:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = seckillSessionService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:seckillsession:info
    public R info(@PathVariable("id") Long id) {
            SeckillSessionEntity seckillSession = seckillSessionService.getById(id);
        return R.ok().put("seckillSession", seckillSession);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:seckillsession:save
    public R save(@RequestBody SeckillSessionEntity seckillSession) {
            seckillSessionService.save(seckillSession);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:seckillsession:update
    public R update(@RequestBody SeckillSessionEntity seckillSession) {
            seckillSessionService.updateById(seckillSession);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillsession:delete")
    public R delete(@RequestBody Long[] ids) {
            seckillSessionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
