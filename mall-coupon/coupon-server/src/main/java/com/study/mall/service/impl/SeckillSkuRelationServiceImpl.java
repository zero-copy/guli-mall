package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.SeckillSkuRelationEntity;
import com.study.mall.mapper.SeckillSkuRelationMapper;
import com.study.mall.service.ISeckillSkuRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Service("seckillSkuRelationService")
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationMapper, SeckillSkuRelationEntity> implements ISeckillSkuRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<SeckillSkuRelationEntity> queryWrapper = new QueryWrapper<>();
        String promotionSessionId = (String) params.get("promotionSessionId");
        if (StringUtils.isNotBlank(promotionSessionId)) {
            queryWrapper.eq(SeckillSkuRelationEntity.PROMOTION_SESSION_ID, promotionSessionId);
        }
        IPage<SeckillSkuRelationEntity> page = this.page(
                new Query<SeckillSkuRelationEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

}