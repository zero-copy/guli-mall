package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.MemberPriceEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.IMemberPriceService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * 商品会员价格
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/memberprice")
public class MemberPriceController {

    @Resource
    private IMemberPriceService memberPriceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:memberprice:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberPriceService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:memberprice:info
    public R info(@PathVariable("id") Long id) {
            MemberPriceEntity memberPrice = memberPriceService.getById(id);
        return R.ok().put("memberPrice", memberPrice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:memberprice:save
    public R save(@RequestBody MemberPriceEntity memberPrice) {
            memberPriceService.save(memberPrice);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:memberprice:update
    public R update(@RequestBody MemberPriceEntity memberPrice) {
            memberPriceService.updateById(memberPrice);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:memberprice:delete")
    public R delete(@RequestBody Long[] ids) {
            memberPriceService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
