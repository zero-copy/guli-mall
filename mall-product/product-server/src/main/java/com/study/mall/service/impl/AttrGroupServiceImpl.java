package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.AttrAttrgroupRelationEntity;
import com.study.mall.entity.AttrGroupEntity;
import com.study.mall.mapper.AttrGroupMapper;
import com.study.mall.service.IAttrAttrgroupRelationService;
import com.study.mall.service.IAttrGroupService;
import com.study.mall.vo.SpuItemAttrGroupVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Service("attrGroupService")
@Transactional(rollbackFor = Exception.class)
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity> implements IAttrGroupService {

    @Resource
    private IAttrAttrgroupRelationService relationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCatelogId(Map<String, Object> params, Long catelogId) {
        IPage<AttrGroupEntity> page;
        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(content ->
                    content.eq(AttrGroupEntity.ATTR_GROUP_ID, key).or().like(AttrGroupEntity.ATTR_GROUP_NAME, key)
            );
        }
        if (catelogId != 0) {
            wrapper.eq(AttrGroupEntity.CATELOG_ID, catelogId);
        }
        page = page(new Query<AttrGroupEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }

    @Override
    public boolean removeRelation(List<AttrAttrgroupRelationEntity> relations) {
        return relationService.removeBatchRelation(relations);
    }

    @Override
    public List<SpuItemAttrGroupVo> getWithAttrBySpuId(Long spuId, Long catalogId) {
        return baseMapper.getWithAttrBySpuId(spuId, catalogId);
    }

}