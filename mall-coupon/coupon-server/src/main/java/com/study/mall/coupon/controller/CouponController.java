package com.study.mall.coupon.controller;

import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;
import com.study.mall.coupon.entity.CouponEntity;
import com.study.mall.coupon.service.ICouponService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 优惠券信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {

    @Resource
    private ICouponService couponService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:coupon:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:coupon:info
    public R info(@PathVariable("id") Long id) {
            CouponEntity coupon = couponService.getById(id);
        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:coupon:save
    public R save(@RequestBody CouponEntity coupon) {
            couponService.save(coupon);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:coupon:update
    public R update(@RequestBody CouponEntity coupon) {
            couponService.updateById(coupon);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:coupon:delete")
    public R delete(@RequestBody Long[] ids) {
            couponService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
