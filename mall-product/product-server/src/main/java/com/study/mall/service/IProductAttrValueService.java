package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
public interface IProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过spuId更新规格参数
     * @param spuId spuId
     * @param attrValueEntities 规格参数
     * @return 是否成功
     */
    boolean updateBySpuId(Long spuId, List<ProductAttrValueEntity> attrValueEntities);
}

