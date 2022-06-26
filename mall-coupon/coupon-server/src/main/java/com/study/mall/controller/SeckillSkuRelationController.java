package com.study.mall.controller;

import com.study.mall.common.lang.R;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.SeckillSkuRelationEntity;
import com.study.mall.service.ISeckillSkuRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 秒杀活动商品关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/seckillskurelation")
public class SeckillSkuRelationController {

    @Resource
    private ISeckillSkuRelationService seckillSkuRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:seckillskurelation:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = seckillSkuRelationService.queryPage(params);
        return R.put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:seckillskurelation:info
    public R info(@PathVariable("id") Long id) {
            SeckillSkuRelationEntity seckillSkuRelation = seckillSkuRelationService.getById(id);
        return R.ok().put("seckillSkuRelation", seckillSkuRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:seckillskurelation:save
    public R save(@RequestBody SeckillSkuRelationEntity seckillSkuRelation) {
            seckillSkuRelationService.save(seckillSkuRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:seckillskurelation:update
    public R update(@RequestBody SeckillSkuRelationEntity seckillSkuRelation) {
            seckillSkuRelationService.updateById(seckillSkuRelation);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillskurelation:delete")
    public R delete(@RequestBody Long[] ids) {
            seckillSkuRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
