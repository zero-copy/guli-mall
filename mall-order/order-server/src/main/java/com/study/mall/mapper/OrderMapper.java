package com.study.mall.mapper;

import com.study.mall.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 订单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

    void updateStatus(@Param("orderSn") String orderSn, @Param("code") Integer code);
}
