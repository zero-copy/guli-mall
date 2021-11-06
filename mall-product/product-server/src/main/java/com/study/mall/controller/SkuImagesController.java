package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.SkuImagesEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.ISkuImagesService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * sku图片
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/skuimages")
public class SkuImagesController {

    @Resource
    private ISkuImagesService skuImagesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:skuimages:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuImagesService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //product:skuimages:info
    public R info(@PathVariable("id") Long id) {
            SkuImagesEntity skuImages = skuImagesService.getById(id);
        return R.ok().put("skuImages", skuImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:skuimages:save
    public R save(@RequestBody SkuImagesEntity skuImages) {
            skuImagesService.save(skuImages);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:skuimages:update
    public R update(@RequestBody SkuImagesEntity skuImages) {
            skuImagesService.updateById(skuImages);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:skuimages:delete")
    public R delete(@RequestBody Long[] ids) {
            skuImagesService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
