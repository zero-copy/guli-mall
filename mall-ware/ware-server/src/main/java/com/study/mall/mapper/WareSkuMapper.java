package com.study.mall.mapper;

import com.study.mall.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param skuId sku
     * @param wareId ware
     * @param skuNum 数量
     * @return 影响行数
     */
    int addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);
}
