package com.study.mall.product.controller;

import cn.hutool.core.bean.BeanUtil;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;
import com.study.mall.product.dto.BrandStatusDto;
import com.study.mall.product.entity.BrandEntity;
import com.study.mall.product.service.IBrandService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


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
    @GetMapping("/list")
    //product:brand:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{brandId}")
    //product:brand:info
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);
        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //product:brand:save
    public R save(@RequestBody BrandEntity brand) {
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //product:brand:update
    public R update(@RequestBody BrandEntity brand) {
        brandService.updateById(brand);
        return R.ok();
    }

    @PostMapping("/update/status")
    public R updateStatus(@RequestBody BrandStatusDto brandStatus) {
        BrandEntity brand = new BrandEntity();
        BeanUtil.copyProperties(brandStatus, brand);
        brandService.updateById(brand);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));
        return R.ok();
    }

}
