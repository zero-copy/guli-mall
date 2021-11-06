package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.CouponSpuRelationEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.ICouponSpuRelationService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * 优惠券与产品关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@RestController
@RequestMapping("coupon/couponspurelation")
public class CouponSpuRelationController {

    @Resource
    private ICouponSpuRelationService couponSpuRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:couponspurelation:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponSpuRelationService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:couponspurelation:info
    public R info(@PathVariable("id") Long id) {
            CouponSpuRelationEntity couponSpuRelation = couponSpuRelationService.getById(id);
        return R.ok().put("couponSpuRelation", couponSpuRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:couponspurelation:save
    public R save(@RequestBody CouponSpuRelationEntity couponSpuRelation) {
            couponSpuRelationService.save(couponSpuRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:couponspurelation:update
    public R update(@RequestBody CouponSpuRelationEntity couponSpuRelation) {
            couponSpuRelationService.updateById(couponSpuRelation);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:couponspurelation:delete")
    public R delete(@RequestBody Long[] ids) {
            couponSpuRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
