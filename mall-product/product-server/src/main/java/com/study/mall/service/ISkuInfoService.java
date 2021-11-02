package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.SkuInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
public interface ISkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 更具条件查询Sku
     * @param params 查询条件
     * @return 分页信息
     */
    PageUtils queryPageByCondition(Map<String, Object> params);

    /**
     * 通过SpuId查询Sku
     * @param spuId spuId
     * @return sku
     */
    List<SkuInfoEntity> getBySpuId(Long spuId);
}

