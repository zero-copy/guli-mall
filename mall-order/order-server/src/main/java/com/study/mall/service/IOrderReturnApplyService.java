package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.OrderReturnApplyEntity;
import java.util.Map;

/**
 * 订单退货申请
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
public interface IOrderReturnApplyService extends IService<OrderReturnApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

