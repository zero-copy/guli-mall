package com.study.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.product.entity.CategoryEntity;
import com.study.mall.product.mapper.CategoryMapper;
import com.study.mall.product.service.ICategoryBrandRelationService;
import com.study.mall.product.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品三级分类
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Slf4j
@Service("categoryService")
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements ICategoryService {

    @Resource
    private ICategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {
        List<CategoryEntity> allCategoryList = list();
        return allCategoryList.stream()
                .filter(root -> root.getParentCid() == 0)
                .map(root -> {
                    root.setChildren(getChildren(root, allCategoryList));
                    return root;
                })
                .sorted(Comparator.comparingLong(item -> (Objects.isNull(item.getSort()) ? 0L : item.getSort())))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkAmdRemoveByIds(List<Long> catIds) {
        //TODO 检查菜单是否被引用
        return removeByIds(catIds);
    }

    @Override
    public List<Long> findCatelogPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, path);
        Collections.reverse(parentPath);
        return parentPath;
    }

    @Override
    public boolean updateCased(CategoryEntity category) {
        boolean isSuccess = updateById(category);
        if (isSuccess && StringUtils.isNotEmpty(category.getName())) {
            return categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
        }
        return false;
    }

    /**
     * 递归查询子菜单
     * @param parent 当前菜单
     * @param allCategoryList 子菜单
     * @return 子菜单
     */
    private List<CategoryEntity> getChildren(CategoryEntity parent, List<CategoryEntity> allCategoryList) {
        return allCategoryList.stream()
                .filter(child -> child.getParentCid().equals(parent.getCatId()))
                .map(curr -> {
                    curr.setChildren(getChildren(curr, allCategoryList));
                    return curr;
                })
                .sorted(Comparator.comparingLong(item -> (Objects.isNull(item.getSort()) ? 0L : item.getSort())))
                .collect(Collectors.toList());
    }

    private List<Long> findParentPath(Long catelogId, List<Long> path) {
        CategoryEntity category = getById(catelogId);
        path.add(catelogId);
        if (category.getParentCid() != 0) {
            findParentPath(category.getParentCid(), path);
        }
        return path;
    }
}