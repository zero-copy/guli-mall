package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.OrderEntity;
import com.study.mall.vo.OrderConfirmVo;
import com.study.mall.vo.OrderSubmitRespVo;
import com.study.mall.vo.OrderSubmitVo;
import com.study.mall.vo.PayVo;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
public interface IOrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 订单确认
     * @return 确认数据
     */
    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    OrderSubmitRespVo submitOrder(OrderSubmitVo submit);

    /**
     * 通过订单号查询订单
     * @param orderSn 订单号
     * @return 订单信息
     */
    OrderEntity getByOrderSn(String orderSn);

    /**
     * 关闭订单
     * @param order 订单信息
     */
    void closeOrder(OrderEntity order);

    /**
     * 获取支付信息
     * @param orderSn 订单号
     * @return 支付信息
     */
    PayVo getOrderPay(String orderSn);

    PageUtils<OrderEntity> queryPageWithItem(Map<String, Object> params);
}

