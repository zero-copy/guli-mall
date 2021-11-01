package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.ProductAttrValueEntity;
import com.study.mall.mapper.ProductAttrValueMapper;
import com.study.mall.service.IProductAttrValueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Service("productAttrValueService")
@Transactional(rollbackFor = Exception.class)
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValueEntity> implements IProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public boolean updateBySpuId(Long spuId, List<ProductAttrValueEntity> attrValueEntities) {
        remove(new QueryWrapper<ProductAttrValueEntity>().eq(ProductAttrValueEntity.SPU_ID, spuId));
        attrValueEntities.forEach(attrValue -> attrValue.setSpuId(spuId));
        return saveBatch(attrValueEntities);
    }

}