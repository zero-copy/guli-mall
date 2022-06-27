package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.SeckillSessionEntity;
import com.study.mall.entity.SeckillSkuRelationEntity;
import com.study.mall.mapper.SeckillSessionMapper;
import com.study.mall.service.ISeckillSessionService;
import com.study.mall.service.ISeckillSkuRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionMapper, SeckillSessionEntity> implements ISeckillSessionService {

    @Resource
    private ISeckillSkuRelationService seckillSkuRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSessionEntity> page = this.page(
                new Query<SeckillSessionEntity>().getPage(params),
                new QueryWrapper<SeckillSessionEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<SeckillSessionEntity> getLastThreeDaySession() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plus(Duration.ofDays(2));
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
        List<SeckillSessionEntity> sessionEntities = baseMapper.selectList(new QueryWrapper<SeckillSessionEntity>().between(SeckillSessionEntity.START_TIME, startDateTime, endDateTime));
        if (!sessionEntities.isEmpty()) {
            sessionEntities.forEach(session -> {
                List<SeckillSkuRelationEntity> skuRelationEntities = seckillSkuRelationService.list(new QueryWrapper<SeckillSkuRelationEntity>().eq(SeckillSkuRelationEntity.PROMOTION_SESSION_ID, session.getId()));
                session.setRelationSkus(skuRelationEntities);
            });
        }
        return sessionEntities;
    }

}