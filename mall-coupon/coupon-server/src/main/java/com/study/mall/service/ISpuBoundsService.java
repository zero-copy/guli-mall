package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
public interface ISpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

