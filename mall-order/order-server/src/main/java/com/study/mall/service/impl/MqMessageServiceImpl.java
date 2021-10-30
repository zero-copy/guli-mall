package com.study.mall.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.mapper.MqMessageMapper;
import com.study.mall.entity.MqMessageEntity;
import com.study.mall.service.IMqMessageService;

/**
 * 
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@Service("mqMessageService")
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessageEntity> implements IMqMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MqMessageEntity> page = this.page(
                new Query<MqMessageEntity>().getPage(params),
                new QueryWrapper<MqMessageEntity>()
        );
        return new PageUtils(page);
    }

}