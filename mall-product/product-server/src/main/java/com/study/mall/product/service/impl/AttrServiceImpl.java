package com.study.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.product.entity.AttrAttrgroupRelationEntity;
import com.study.mall.product.entity.AttrEntity;
import com.study.mall.product.mapper.AttrAttrgroupRelationMapper;
import com.study.mall.product.mapper.AttrMapper;
import com.study.mall.product.service.IAttrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品属性
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrMapper, AttrEntity> implements IAttrService {

    @Resource
    private AttrAttrgroupRelationMapper relationMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public boolean save(Long groupId, AttrEntity attr) {
        save(attr);
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrId(attr.getAttrId());
        relationEntity.setAttrGroupId(groupId);
        return relationMapper.insert(relationEntity) != 0;
    }

}