package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.WareSkuEntity;
import com.study.mall.common.lang.dto.SkuStockDto;
import com.study.mall.vo.LockStockResultVo;
import com.study.mall.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
public interface IWareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 添加库存
     * @param skuId 商品Id
     * @param wareId 仓库Id
     * @param skuNum 商品数量
     * @return 是否成功
     */
    boolean addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 查询是否有库存
     * @param skuIds skuIds
     * @return 库存信息
     */
    List<SkuStockDto> getStockByIds(List<Long> skuIds);

    /**
     * 锁定库存
     * @param vo
     * @return
     */
    List<LockStockResultVo> orderLockStock(WareSkuLockVo vo);
}

