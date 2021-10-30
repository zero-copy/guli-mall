package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.SkuLadderEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.ISkuLadderService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 商品阶梯价格
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/skuladder")
public class SkuLadderController {

    @Resource
    private ISkuLadderService skuLadderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:skuladder:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuLadderService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:skuladder:info
    public R info(@PathVariable("id") Long id) {
            SkuLadderEntity skuLadder = skuLadderService.getById(id);
        return R.ok().put("skuLadder", skuLadder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:skuladder:save
    public R save(@RequestBody SkuLadderEntity skuLadder) {
            skuLadderService.save(skuLadder);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:skuladder:update
    public R update(@RequestBody SkuLadderEntity skuLadder) {
            skuLadderService.updateById(skuLadder);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:skuladder:delete")
    public R delete(@RequestBody Long[] ids) {
            skuLadderService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
