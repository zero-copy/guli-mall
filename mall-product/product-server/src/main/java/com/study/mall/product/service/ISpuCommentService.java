package com.study.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.common.utils.PageUtils;
import com.study.mall.product.entity.SpuCommentEntity;
import java.util.Map;

/**
 * 商品评价
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
public interface ISpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

