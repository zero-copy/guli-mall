package com.study.mall.controller;

import com.study.mall.common.lang.R;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.SkuSaleAttrValueEntity;
import com.study.mall.service.ISkuSaleAttrValueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * sku销售属性&值
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@RestController
@RequestMapping("product/skusaleattrvalue")
public class SkuSaleAttrValueController {

    @Resource
    private ISkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:skusaleattrvalue:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuSaleAttrValueService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //product:skusaleattrvalue:info
    public R info(@PathVariable("id") Long id) {
        SkuSaleAttrValueEntity skuSaleAttrValue = skuSaleAttrValueService.getById(id);
        return R.ok().put("skuSaleAttrValue", skuSaleAttrValue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:skusaleattrvalue:save
    public R save(@RequestBody SkuSaleAttrValueEntity skuSaleAttrValue) {
        skuSaleAttrValueService.save(skuSaleAttrValue);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:skusaleattrvalue:update
    public R update(@RequestBody SkuSaleAttrValueEntity skuSaleAttrValue) {
        skuSaleAttrValueService.updateById(skuSaleAttrValue);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:skusaleattrvalue:delete")
    public R delete(@RequestBody Long[] ids) {
        skuSaleAttrValueService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/string-list/{skuId}")
    public R<List<String>> getSkuSaleAttrValues(@PathVariable Long skuId) {
        return R.ok(skuSaleAttrValueService.getSkuSaleAttrValuesAsStringList(skuId));
    }

}
