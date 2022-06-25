package com.study.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.mall.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@Mapper
public interface WareSkuMapper extends BaseMapper<WareSkuEntity> {

    /**
     * 添加库存
     *
     * @param skuId  sku
     * @param wareId ware
     * @param skuNum 数量
     * @return 影响行数
     */
    int addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    /**
     * 获取当前库存
     *
     * @param skuId skuId
     * @return 库存量
     */
    Long getStock(Long skuId);

    List<Long> listWareIdHasStock(Long skuId);

    Long lockSkuStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Integer num);

    void unLockStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Integer num, @Param("orderTaskId") Long orderTaskId);
}
