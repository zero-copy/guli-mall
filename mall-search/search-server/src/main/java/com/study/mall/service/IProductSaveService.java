package com.study.mall.service;

import com.study.mall.common.lang.dto.es.SkuEsDto;

import java.io.IOException;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 03 上午 01:31
 */
public interface IProductSaveService {

    /**
     * 商品上架
     * @param skuEsDtos es商品模型
     * @return 是否成功
     */
    boolean statusUp(List<SkuEsDto> skuEsDtos) throws IOException;
}
