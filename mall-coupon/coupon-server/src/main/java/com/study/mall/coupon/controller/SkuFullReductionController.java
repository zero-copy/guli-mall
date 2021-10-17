package com.study.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.coupon.entity.SkuFullReductionEntity;
import com.study.mall.coupon.service.ISkuFullReductionService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 商品满减信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/skufullreduction")
public class SkuFullReductionController {

    @Resource
    private ISkuFullReductionService skuFullReductionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:skufullreduction:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuFullReductionService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:skufullreduction:info
    public R info(@PathVariable("id") Long id) {
            SkuFullReductionEntity skuFullReduction = skuFullReductionService.getById(id);
        return R.ok().put("skuFullReduction", skuFullReduction);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:skufullreduction:save
    public R save(@RequestBody SkuFullReductionEntity skuFullReduction) {
            skuFullReductionService.save(skuFullReduction);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:skufullreduction:update
    public R update(@RequestBody SkuFullReductionEntity skuFullReduction) {
            skuFullReductionService.updateById(skuFullReduction);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:skufullreduction:delete")
    public R delete(@RequestBody Long[] ids) {
            skuFullReductionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
