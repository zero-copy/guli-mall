package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.WareOrderTaskEntity;
import com.study.mall.mapper.WareOrderTaskMapper;
import com.study.mall.service.IWareOrderTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@Slf4j
@Service("wareOrderTaskService")
@Transactional(rollbackFor = Exception.class)
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskMapper, WareOrderTaskEntity> implements IWareOrderTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskEntity> page = this.page(
                new Query<WareOrderTaskEntity>().getPage(params),
                new QueryWrapper<WareOrderTaskEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public WareOrderTaskEntity getByOrderSn(String orderSn) {
        return baseMapper.selectOne(new QueryWrapper<WareOrderTaskEntity>().eq(WareOrderTaskEntity.ORDER_SN, orderSn));
    }

}