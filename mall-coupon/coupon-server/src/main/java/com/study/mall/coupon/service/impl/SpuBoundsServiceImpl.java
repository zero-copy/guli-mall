package com.study.mall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.common.utils.PageUtils;
import com.study.common.utils.Query;
import com.study.mall.coupon.mapper.SpuBoundsMapper;
import com.study.mall.coupon.entity.SpuBoundsEntity;
import com.study.mall.coupon.service.ISpuBoundsService;

/**
 * 商品spu积分设置
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsMapper, SpuBoundsEntity> implements ISpuBoundsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuBoundsEntity> page = this.page(
                new Query<SpuBoundsEntity>().getPage(params),
                new QueryWrapper<SpuBoundsEntity>()
        );
        return new PageUtils(page);
    }

}