package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.MemberLevelEntity;
import com.study.mall.mapper.MemberLevelMapper;
import com.study.mall.service.IMemberLevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 会员等级
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@Service("memberLevelService")
@Transactional(rollbackFor = Exception.class)
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevelEntity> implements IMemberLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberLevelEntity> page = this.page(
                new Query<MemberLevelEntity>().getPage(params),
                new QueryWrapper<MemberLevelEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public MemberLevelEntity getDefaultLevel() {
        return baseMapper.selectOne(new QueryWrapper<MemberLevelEntity>().eq(MemberLevelEntity.DEFAULT_STATUS, 1));
    }

}