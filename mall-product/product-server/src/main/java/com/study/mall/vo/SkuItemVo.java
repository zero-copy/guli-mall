package com.study.mall.vo;

import com.study.mall.entity.SkuImagesEntity;
import com.study.mall.entity.SkuInfoEntity;
import com.study.mall.entity.SpuInfoDescEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 12 19 下午 07:50
 */
@Data
public class SkuItemVo implements Serializable {

    private SkuInfoEntity info;

    private List<SkuImagesEntity> images;

    private List<SkuItemSaleAttrVo> saleAttr;

    private List<SpuItemAttrGroupVo> groupAttrs;

    private SpuInfoDescEntity desp;

    @Data
    public static class SkuItemSaleAttrVo implements Serializable {

        private Long attrId;

        private String attrName;

        private List<String> attrValues;
    }

    @Data
    public static class SpuItemAttrGroupVo implements Serializable {

        private String groupName;

        List<SpuBaseAttrVo> attrs;
    }

    @Data
    public static class SpuBaseAttrVo implements Serializable {

        private String attrName;

        private String attrValue;
    }
}
