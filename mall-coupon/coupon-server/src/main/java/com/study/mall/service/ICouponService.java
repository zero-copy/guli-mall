package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.CouponEntity;

import java.util.Map;

/**
 * 优惠券信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
public interface ICouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

