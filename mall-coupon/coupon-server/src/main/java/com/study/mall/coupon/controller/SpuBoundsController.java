package com.study.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.coupon.entity.SpuBoundsEntity;
import com.study.mall.coupon.service.ISpuBoundsService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 商品spu积分设置
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/spubounds")
public class SpuBoundsController {

    @Resource
    private ISpuBoundsService spuBoundsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:spubounds:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = spuBoundsService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:spubounds:info
    public R info(@PathVariable("id") Long id) {
            SpuBoundsEntity spuBounds = spuBoundsService.getById(id);
        return R.ok().put("spuBounds", spuBounds);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:spubounds:save
    public R save(@RequestBody SpuBoundsEntity spuBounds) {
            spuBoundsService.save(spuBounds);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:spubounds:update
    public R update(@RequestBody SpuBoundsEntity spuBounds) {
            spuBoundsService.updateById(spuBounds);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:spubounds:delete")
    public R delete(@RequestBody Long[] ids) {
            spuBoundsService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
