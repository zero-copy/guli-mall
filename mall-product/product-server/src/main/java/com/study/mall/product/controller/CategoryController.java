package com.study.mall.product.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.product.entity.CategoryEntity;
import com.study.mall.product.service.ICategoryService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * 商品三级分类
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {

    @Resource
    private ICategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:category:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    //product:category:info
    public R info(@PathVariable("catId") Long catId) {
            CategoryEntity category = categoryService.getById(catId);
        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:category:save
    public R save(@RequestBody CategoryEntity category) {
            categoryService.save(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:category:update
    public R update(@RequestBody CategoryEntity category) {
            categoryService.updateById(category);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds) {
            categoryService.removeByIds(Arrays.asList(catIds));
        return R.ok();
    }

}
