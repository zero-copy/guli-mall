package com.study.mall.product.controller;

import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;
import com.study.mall.product.entity.AttrEntity;
import com.study.mall.product.service.IAttrService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 商品属性
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Resource
    private IAttrService attrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:attr:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    //product:attr:info
    public R info(@PathVariable("attrId") Long attrId) {
        AttrEntity attr = attrService.getById(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //product:attr:save
    public R save(@RequestBody AttrEntity attr) {
        attrService.save(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //product:attr:update
    public R update(@RequestBody AttrEntity attr) {
        attrService.updateById(attr);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));
        return R.ok();
    }

}
