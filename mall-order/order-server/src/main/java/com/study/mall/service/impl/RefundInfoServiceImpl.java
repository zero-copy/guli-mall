package com.study.mall.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.mapper.RefundInfoMapper;
import com.study.mall.entity.RefundInfoEntity;
import com.study.mall.service.IRefundInfoService;

/**
 * 退款信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfoEntity> implements IRefundInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RefundInfoEntity> page = this.page(
                new Query<RefundInfoEntity>().getPage(params),
                new QueryWrapper<RefundInfoEntity>()
        );
        return new PageUtils(page);
    }

}