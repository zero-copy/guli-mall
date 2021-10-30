package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.SeckillPromotionEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.ISeckillPromotionService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 秒杀活动
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/seckillpromotion")
public class SeckillPromotionController {

    @Resource
    private ISeckillPromotionService seckillPromotionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:seckillpromotion:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = seckillPromotionService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:seckillpromotion:info
    public R info(@PathVariable("id") Long id) {
            SeckillPromotionEntity seckillPromotion = seckillPromotionService.getById(id);
        return R.ok().put("seckillPromotion", seckillPromotion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:seckillpromotion:save
    public R save(@RequestBody SeckillPromotionEntity seckillPromotion) {
            seckillPromotionService.save(seckillPromotion);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:seckillpromotion:update
    public R update(@RequestBody SeckillPromotionEntity seckillPromotion) {
            seckillPromotionService.updateById(seckillPromotion);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillpromotion:delete")
    public R delete(@RequestBody Long[] ids) {
            seckillPromotionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
