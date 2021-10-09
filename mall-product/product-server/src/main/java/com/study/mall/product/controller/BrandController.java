package com.study.mall.product.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.product.entity.BrandEntity;
import com.study.mall.product.service.IBrandService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * 品牌
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {

    @Resource
    private IBrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:brand:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //product:brand:info
    public R info(@PathVariable("brandId") Long brandId) {
            BrandEntity brand = brandService.getById(brandId);
        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:brand:save
    public R save(@RequestBody BrandEntity brand) {
            brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:brand:update
    public R update(@RequestBody BrandEntity brand) {
            brandService.updateById(brand);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds) {
            brandService.removeByIds(Arrays.asList(brandIds));
        return R.ok();
    }

}
