package com.study.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.common.utils.PageUtils;
import com.study.common.utils.Query;
import com.study.mall.product.entity.CategoryEntity;
import com.study.mall.product.mapper.CategoryMapper;
import com.study.mall.product.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商品三级分类
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements ICategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
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
}