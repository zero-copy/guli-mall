package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.AttrAttrgroupRelationEntity;
import com.study.mall.entity.AttrGroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
public interface IAttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过分类查询属性分组
     * @param params 分页参数
     * @param catelogId 分类ID
     * @return 分页数据
     */
    PageUtils queryPageByCatelogId(Map<String, Object> params, Long catelogId);

    /**
     * 移除当前分组中的属性
     * @param relations 分组-属性
     * @return 是否成功
     */
    boolean removeRelation(List<AttrAttrgroupRelationEntity> relations);
}

