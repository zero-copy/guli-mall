package com.study.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.product.entity.CategoryBrandRelationEntity;
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
}

