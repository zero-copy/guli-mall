package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
public interface ICategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查出所有分类以及子分类
     * @return 分类
     */
    List<CategoryEntity> listTree();

    /**
     * 检查删除 判断是否被引用
     * @param catIds ids
     * @return 是否成功
     */
    boolean checkAmdRemoveByIds(List<Long> catIds);

    /**
     * 通过分类Id查询完整分类路径
     * @param catelogId 分类id
     * @return 完整路径id
     */
    List<Long> findCatelogPath(Long catelogId);

    /**
     * 级联更新
     * @param category 分类信息
     */
    boolean updateCased(CategoryEntity category);
}

