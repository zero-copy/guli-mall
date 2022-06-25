package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.WareOrderTaskEntity;
import java.util.Map;

/**
 * 库存工作单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
public interface IWareOrderTaskService extends IService<WareOrderTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过订单号查询任务单
     * @param orderSn 订单号
     * @return 任务单信息
     */
    WareOrderTaskEntity getByOrderSn(String orderSn);
}

