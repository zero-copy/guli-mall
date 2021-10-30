package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.BrandEntity;
import com.study.mall.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
public interface ICategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保持品牌与分类关系
     * @param categoryBrandRelation 关系
     * @return 是否成功
     */
    boolean saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 更新品牌名
     * @param brandId 品牌ID
     * @param brandName 品牌名
     * @return 是否成功
     */
    boolean updateBrand(Long brandId, String brandName);

    /**
     * 更新分类名
     * @param categoryId 分类ID
     * @param categoryName 分类名
     * @return 是否成功
     */
    boolean updateCategory(Long categoryId, String categoryName);

    /**
     * 通过分类ID查询品牌
     * @param catId 分类ID
     * @return 品牌信息
     */
    List<BrandEntity> getBrandByCatId(Long catId);
}

