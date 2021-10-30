package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.CouponHistoryEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.ICouponHistoryService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 优惠券领取历史记录
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@RestController
@RequestMapping("coupon/couponhistory")
public class CouponHistoryController {

    @Resource
    private ICouponHistoryService couponHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:couponhistory:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponHistoryService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:couponhistory:info
    public R info(@PathVariable("id") Long id) {
            CouponHistoryEntity couponHistory = couponHistoryService.getById(id);
        return R.ok().put("couponHistory", couponHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:couponhistory:save
    public R save(@RequestBody CouponHistoryEntity couponHistory) {
            couponHistoryService.save(couponHistory);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:couponhistory:update
    public R update(@RequestBody CouponHistoryEntity couponHistory) {
            couponHistoryService.updateById(couponHistory);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:couponhistory:delete")
    public R delete(@RequestBody Long[] ids) {
            couponHistoryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
