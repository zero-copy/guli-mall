package com.study.mall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.product.mapper.SpuCommentMapper;
import com.study.mall.product.entity.SpuCommentEntity;
import com.study.mall.product.service.ISpuCommentService;

/**
 * 商品评价
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Service("spuCommentService")
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentMapper, SpuCommentEntity> implements ISpuCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuCommentEntity> page = this.page(
                new Query<SpuCommentEntity>().getPage(params),
                new QueryWrapper<SpuCommentEntity>()
        );
        return new PageUtils(page);
    }

}