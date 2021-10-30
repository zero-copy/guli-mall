package com.study.mall.mapper;

import com.study.mall.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItemEntity> {

}
