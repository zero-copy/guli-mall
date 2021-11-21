package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.CategoryEntity;
import com.study.mall.mapper.CategoryMapper;
import com.study.mall.service.ICategoryBrandRelationService;
import com.study.mall.service.ICategoryService;
import com.study.mall.vo.Catelog2Vo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redisson;

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

    @CacheEvict(value = "category", allEntries = true)
    @Override
    public boolean updateCased(CategoryEntity category) {
        boolean isSuccess = updateById(category);
        if (isSuccess && StringUtils.isNotEmpty(category.getName())) {
            return categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
        }
        return false;
    }

    @Cacheable(value = "category", key = "#root.methodName")
    @Override
    public List<CategoryEntity> getRoot() {
        return list(new QueryWrapper<CategoryEntity>().eq(CategoryEntity.PARENT_CID, 0));
    }

    @Cacheable(value = "category", key = "#root.methodName")
    @Override
    public Map<String, List<Catelog2Vo>> getJsonMap() {
        //查询所有
        List<CategoryEntity> sourceList = list();
        //获取root
        List<CategoryEntity> root = getChild(sourceList, 0L);
        return root.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            List<CategoryEntity> child = getChild(sourceList, v.getCatId());
            return child.stream().map(item -> {
                //查询3级分类
                List<Catelog2Vo.Catelog3Vo> catelog3Vos = getChild(sourceList, item.getCatId())
                        .stream().map(i3 -> new Catelog2Vo.Catelog3Vo(item.getCatId().toString(), i3.getCatId().toString(), i3.getName()))
                        .collect(Collectors.toList());
                return new Catelog2Vo(v.getCatId().toString(), catelog3Vos, item.getCatId().toString(), item.getName());
            }).collect(Collectors.toList());
        }));
    }

    /**
     * 获取子分类
     * @param sourceList 源数据
     * @param parentCatId 父分类Id
     * @return 子分类
     */
    private List<CategoryEntity> getChild(List<CategoryEntity> sourceList,Long parentCatId) {
        return sourceList.stream()
                .filter(item -> item.getParentCid().equals(parentCatId))
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

    private List<Long> findParentPath(Long catelogId, List<Long> path) {
        CategoryEntity category = getById(catelogId);
        path.add(catelogId);
        if (category.getParentCid() != 0) {
            findParentPath(category.getParentCid(), path);
        }
        return path;
    }
}