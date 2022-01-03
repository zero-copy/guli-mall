package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.SkuImagesEntity;
import com.study.mall.mapper.SkuImagesMapper;
import com.study.mall.service.ISkuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesMapper, SkuImagesEntity> implements ISkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<SkuImagesEntity> getBySkuId(Long skuId) {
        return list(new QueryWrapper<SkuImagesEntity>().eq(SkuImagesEntity.SKU_ID, skuId));
    }

}