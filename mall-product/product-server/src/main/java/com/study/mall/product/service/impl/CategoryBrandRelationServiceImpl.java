package com.study.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.product.entity.BrandEntity;
import com.study.mall.product.entity.CategoryBrandRelationEntity;
import com.study.mall.product.entity.CategoryEntity;
import com.study.mall.product.mapper.CategoryBrandRelationMapper;
import com.study.mall.product.service.IBrandService;
import com.study.mall.product.service.ICategoryBrandRelationService;
import com.study.mall.product.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 品牌分类关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Service("categoryBrandRelationService")
@Transactional(rollbackFor = Exception.class)
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelationEntity> implements ICategoryBrandRelationService {

    @Resource
    private IBrandService brandService;

    @Resource
    private ICategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId)) {
            wrapper.eq(CategoryBrandRelationEntity.BRAND_ID, brandId);
        }
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public boolean saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        BrandEntity brandEntity = brandService.getById(brandId);
        CategoryEntity categoryEntity = categoryService.getById(catelogId);
        if (Objects.nonNull(categoryEntity) && Objects.nonNull(brandEntity)) {
            categoryBrandRelation.setBrandName(brandEntity.getName());
            categoryBrandRelation.setCatelogName(categoryEntity.getName());
            return save(categoryBrandRelation);
        }
        return false;
    }

    @Override
    public boolean updateBrand(Long brandId, String brandName) {
        CategoryBrandRelationEntity relationEntity = new CategoryBrandRelationEntity();
        relationEntity.setBrandId(brandId);
        relationEntity.setBrandName(brandName);
        return update(relationEntity, new UpdateWrapper<CategoryBrandRelationEntity>()
                .eq(CategoryBrandRelationEntity.BRAND_ID, brandId));
    }

    @Override
    public boolean updateCategory(Long categoryId, String categoryName) {
        CategoryBrandRelationEntity relationEntity = new CategoryBrandRelationEntity();
        relationEntity.setCatelogId(categoryId);
        relationEntity.setCatelogName(categoryName);
        return update(relationEntity, new UpdateWrapper<CategoryBrandRelationEntity>()
                .eq(CategoryBrandRelationEntity.CATELOG_ID, categoryId));
    }

    @Override
    public List<BrandEntity> getBrandByCatId(Long catId) {
        List<CategoryBrandRelationEntity> relationEntities = list(new QueryWrapper<CategoryBrandRelationEntity>()
                .eq(CategoryBrandRelationEntity.CATELOG_ID, catId));
        List<Long> brandIds = relationEntities.stream().map(CategoryBrandRelationEntity::getBrandId)
                .collect(Collectors.toList());
        return brandService.listByIds(brandIds);
    }

}