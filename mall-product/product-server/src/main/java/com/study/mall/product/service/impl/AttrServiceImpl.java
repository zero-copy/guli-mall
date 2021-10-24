package com.study.mall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.product.entity.AttrAttrgroupRelationEntity;
import com.study.mall.product.entity.AttrEntity;
import com.study.mall.product.entity.AttrGroupEntity;
import com.study.mall.product.entity.CategoryEntity;
import com.study.mall.product.mapper.AttrAttrgroupRelationMapper;
import com.study.mall.product.mapper.AttrGroupMapper;
import com.study.mall.product.mapper.AttrMapper;
import com.study.mall.product.mapper.CategoryMapper;
import com.study.mall.product.service.IAttrService;
import com.study.mall.product.vo.AttrRespVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商品属性
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Service("attrService")
@Transactional(rollbackFor = Exception.class)
public class AttrServiceImpl extends ServiceImpl<AttrMapper, AttrEntity> implements IAttrService {

    @Resource
    private AttrAttrgroupRelationMapper relationMapper;

    @Resource
    private AttrGroupMapper attrGroupMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<>()
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

    @Override
    public PageUtils queryBaseAttrPage(Long catelogId, Map<String, Object> params) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        if (catelogId != 0) {
            wrapper.eq(AttrEntity.CATELOG_ID, catelogId);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(content -> content.eq(AttrEntity.ATTR_ID, key)
                    .or().like(AttrEntity.ATTR_NAME, key));
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );
        List<AttrRespVo> attrRespVos = page.getRecords().stream().map(attr -> {
            AttrRespVo respVo = BeanUtil.copyProperties(attr, AttrRespVo.class);
            AttrAttrgroupRelationEntity relationEntity = relationMapper.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>()
                    .eq(AttrAttrgroupRelationEntity.ATTR_ID, attr.getAttrId())
            );
            if (Objects.nonNull(relationEntity)) {
                AttrGroupEntity groupEntity = attrGroupMapper.selectById(relationEntity.getAttrGroupId());
                respVo.setGroupName(groupEntity.getAttrGroupName());
            }
            CategoryEntity categoryEntity = categoryMapper.selectById(attr.getCatelogId());
            if (Objects.nonNull(categoryEntity)) {
                respVo.setCatelogName(categoryEntity.getName());
            }
            return respVo;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(attrRespVos);
        return pageUtils;
    }

}