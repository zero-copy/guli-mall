package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.BrandEntity;
import com.study.mall.mapper.BrandMapper;
import com.study.mall.service.IBrandService;
import com.study.mall.service.ICategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 品牌
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Service("brandService")
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandEntity> implements IBrandService {

    @Resource
    private ICategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.eq(BrandEntity.BRAND_ID, key).or().like(BrandEntity.NAME, key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public boolean updateDetail(BrandEntity brand) {
        boolean isSuccess = updateById(brand);
        //同步更新关联表
        if (isSuccess && StringUtils.isNotEmpty(brand.getName())) {
            return categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());
        }
        return false;
    }

}