package com.study.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.coupon.entity.CouponSpuCategoryRelationEntity;
import com.study.mall.coupon.service.ICouponSpuCategoryRelationService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 优惠券分类关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@RestController
@RequestMapping("coupon/couponspucategoryrelation")
public class CouponSpuCategoryRelationController {

    @Resource
    private ICouponSpuCategoryRelationService couponSpuCategoryRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:couponspucategoryrelation:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponSpuCategoryRelationService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:couponspucategoryrelation:info
    public R info(@PathVariable("id") Long id) {
            CouponSpuCategoryRelationEntity couponSpuCategoryRelation = couponSpuCategoryRelationService.getById(id);
        return R.ok().put("couponSpuCategoryRelation", couponSpuCategoryRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:couponspucategoryrelation:save
    public R save(@RequestBody CouponSpuCategoryRelationEntity couponSpuCategoryRelation) {
            couponSpuCategoryRelationService.save(couponSpuCategoryRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:couponspucategoryrelation:update
    public R update(@RequestBody CouponSpuCategoryRelationEntity couponSpuCategoryRelation) {
            couponSpuCategoryRelationService.updateById(couponSpuCategoryRelation);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:couponspucategoryrelation:delete")
    public R delete(@RequestBody Long[] ids) {
            couponSpuCategoryRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
