package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.SkuInfoEntity;
import com.study.mall.mapper.SkuInfoMapper;
import com.study.mall.service.ISkuInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * sku信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfoEntity> implements ISkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(w ->
                    w.eq(SkuInfoEntity.SKU_ID, key).or().like(SkuInfoEntity.SKU_NAME, key));
        }
        String catelogId = (String) params.get("catelogId");
        if (StringUtils.isNotBlank(catelogId) && !"0".equals(catelogId)) {
            wrapper.eq(SkuInfoEntity.CATALOG_ID, catelogId);
        }
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId) && !"0".equals(brandId)) {
            wrapper.eq(SkuInfoEntity.BRAND_ID, brandId);
        }
        String min = (String) params.get("min");
        if (StringUtils.isNotBlank(min)) {
            wrapper.ge(SkuInfoEntity.PRICE, min);
        }
        String maxStr = (String) params.get("max");
        if (StringUtils.isNotBlank(maxStr)) {
            BigDecimal max = new BigDecimal(maxStr);
            if (max.compareTo(BigDecimal.valueOf(0)) > 0) {
                wrapper.le(SkuInfoEntity.PRICE, max);
            }
        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

}