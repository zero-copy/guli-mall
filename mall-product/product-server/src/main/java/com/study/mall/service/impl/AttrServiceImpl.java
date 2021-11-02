package com.study.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.constant.ProductConstant;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.AttrAttrgroupRelationEntity;
import com.study.mall.entity.AttrEntity;
import com.study.mall.entity.AttrGroupEntity;
import com.study.mall.entity.CategoryEntity;
import com.study.mall.mapper.AttrMapper;
import com.study.mall.service.IAttrAttrgroupRelationService;
import com.study.mall.service.IAttrGroupService;
import com.study.mall.service.IAttrService;
import com.study.mall.service.ICategoryService;
import com.study.mall.vo.AttrReqVo;
import com.study.mall.vo.AttrRespVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
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
    private IAttrAttrgroupRelationService relationService;

    @Resource
    private IAttrGroupService attrGroupService;

    @Resource
    private ICategoryService categoryService;

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
        boolean isSuccess = save(attr);
        if (attr.getAttrType().equals(ProductConstant.AttrEnum.ATTR_TYPE_BASE.getValue()) || Objects.nonNull(groupId)) {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrId(attr.getAttrId());
            relationEntity.setAttrGroupId(groupId);
            return relationService.save(relationEntity) && isSuccess;
        }
        return isSuccess;
    }

    public PageUtils queryAttrPage(Integer attrType, Long catelogId, Map<String, Object> params) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq(AttrEntity.ATTR_TYPE, attrType);
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
            AttrAttrgroupRelationEntity relationEntity = relationService.getOne(new QueryWrapper<AttrAttrgroupRelationEntity>()
                    .eq(AttrAttrgroupRelationEntity.ATTR_ID, attr.getAttrId())
            );
            if (attrType.equals(ProductConstant.AttrEnum.ATTR_TYPE_BASE.getValue()) && Objects.nonNull(relationEntity)) {
                AttrGroupEntity groupEntity = attrGroupService.getById(relationEntity.getAttrGroupId());
                respVo.setGroupName(groupEntity.getAttrGroupName());
            }
            CategoryEntity categoryEntity = categoryService.getById(attr.getCatelogId());
            if (Objects.nonNull(categoryEntity)) {
                respVo.setCatelogName(categoryEntity.getName());
            }
            return respVo;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(attrRespVos);
        return pageUtils;
    }

    @Override
    public AttrRespVo getDetailById(Long attrId) {
        AttrEntity attrEntity = getById(attrId);
        if (Objects.isNull(attrEntity)) {
            return null;
        }
        AttrRespVo attrRespVo = BeanUtil.copyProperties(attrEntity, AttrRespVo.class);
        AttrAttrgroupRelationEntity relationEntity = relationService.getOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq(AttrAttrgroupRelationEntity.ATTR_ID, attrId));
        if (Objects.nonNull(relationEntity)) {
            attrRespVo.setAttrGroupId(relationEntity.getAttrGroupId());
            AttrGroupEntity attrGroupEntity = attrGroupService.getById(relationEntity.getAttrGroupId());
            if (Objects.nonNull(attrGroupEntity)) {
                attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
            }
        }
        List<Long> catelogPath = categoryService.findCatelogPath(attrEntity.getCatelogId());
        attrRespVo.setCatelogPath(catelogPath);
        CategoryEntity categoryEntity = categoryService.getById(attrEntity.getCatelogId());
        if (Objects.nonNull(categoryEntity)) {
            attrRespVo.setCatelogName(categoryEntity.getName());
        }
        return attrRespVo;
    }

    @Override
    public boolean updateDetail(AttrReqVo attr) {
        AttrEntity attrEntity = BeanUtil.copyProperties(attr, AttrEntity.class);
        if (attr.getAttrType().equals(ProductConstant.AttrEnum.ATTR_TYPE_SALE.getValue())) {
            return updateById(attrEntity);
        } else {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            long count = relationService.count(new UpdateWrapper<AttrAttrgroupRelationEntity>()
                    .eq(AttrAttrgroupRelationEntity.ATTR_ID, attr.getAttrId()));
            if (count > 0) {
                return relationService.update(relationEntity, new UpdateWrapper<AttrAttrgroupRelationEntity>()
                        .eq(AttrAttrgroupRelationEntity.ATTR_ID, attr.getAttrId())
                );
            } else {
                return relationService.save(relationEntity);
            }
        }
    }

    @Override
    public List<AttrEntity> getGroupAttr(Long attrGroupId) {
        List<AttrAttrgroupRelationEntity> relationEntities = relationService.list(
                new QueryWrapper<>(new AttrAttrgroupRelationEntity(), AttrAttrgroupRelationEntity.ATTR_ID)
                        .eq(AttrAttrgroupRelationEntity.ATTR_GROUP_ID, attrGroupId)
        );
        List<Long> attrIds = relationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        if (attrIds.isEmpty()) {
            return Collections.emptyList();
        }
        return listByIds(attrIds);
    }

    @Override
    public PageUtils getNoRelation(Long attrGroupId, Map<String, Object> params) {
        AttrGroupEntity groupEntity = attrGroupService.getById(attrGroupId);
        Long catelogId = groupEntity.getCatelogId();
        List<AttrGroupEntity> groupEntities = attrGroupService.list(new QueryWrapper<AttrGroupEntity>()
                .eq(AttrGroupEntity.CATELOG_ID, catelogId));
        List<Long> groupIds = groupEntities.stream().map(AttrGroupEntity::getAttrGroupId).collect(Collectors.toList());
        List<AttrAttrgroupRelationEntity> relationEntities = relationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>()
                .in(AttrAttrgroupRelationEntity.ATTR_GROUP_ID, groupIds)
        );
        List<Long> attrIds = relationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>()
                .eq(AttrEntity.CATELOG_ID, catelogId)
                .eq(AttrEntity.ATTR_TYPE, ProductConstant.AttrEnum.ATTR_TYPE_BASE.getValue());
        if (!attrIds.isEmpty()) {
            wrapper.notIn(AttrEntity.ATTR_ID, attrIds);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(content -> content.eq(AttrEntity.ATTR_ID, key).or().like(AttrEntity.ATTR_NAME, key));
        }
        IPage<AttrEntity> page = page(new Query<AttrEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }

    @Override
    public List<Long> getSearchType(List<Long> attrIds) {
        return baseMapper.selectSearchType(attrIds);
    }

}