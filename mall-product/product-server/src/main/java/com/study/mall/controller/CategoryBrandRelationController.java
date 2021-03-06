package com.study.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;
import com.study.mall.entity.BrandEntity;
import com.study.mall.entity.CategoryBrandRelationEntity;
import com.study.mall.service.ICategoryBrandRelationService;
import com.study.mall.vo.BrandVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 品牌分类关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {

    @Resource
    private ICategoryBrandRelationService categoryBrandRelationService;

    @GetMapping("/brands/list")
    public R brandsList(@RequestParam Long catId) {
        List<BrandEntity> brandEntities = categoryBrandRelationService.getBrandByCatId(catId);
        List<BrandVo> brandVos = brandEntities.stream().map(item -> {
                    BrandVo brandVo = new BrandVo();
                    brandVo.setBrandId(item.getBrandId());
                    brandVo.setBrandName(item.getName());
                    return brandVo;
                }).collect(Collectors.toList());
        return R.ok(brandVos);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    //product:categorybrandrelation:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryBrandRelationService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/catelog/list")
    public R listByBrandId(@Validated @NotBlank String brandId) {
        List<CategoryBrandRelationEntity> list = categoryBrandRelationService.list(new QueryWrapper<CategoryBrandRelationEntity>()
                .eq(CategoryBrandRelationEntity.BRAND_ID, brandId));
        return R.ok(list);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //product:categorybrandrelation:info
    public R info(@PathVariable("id") Long id) {
        CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);
        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //product:categorybrandrelation:save
    public R save(@RequestBody @Validated CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.saveDetail(categoryBrandRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //product:categorybrandrelation:update
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.updateById(categoryBrandRelation);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids) {
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
