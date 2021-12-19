package com.study.mall.vo;

import com.study.mall.common.lang.dto.es.SkuEsDto;
import lombok.Data;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 27 下午 05:49
 */
@Data
public class SearchVo {

    /**
     * 商品信息
     */
    private List<SkuEsDto> products;

    /**
     * 品牌集合
     */
    private List<BrandVo> brands;

    /**
     * 属性集合
     */
    private List<AttrVo> attrs;

    /**
     * 分类集合
     */
    private List<CatalogVo> catalogs;

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页码
     */
    private Long totalPages;

    /**
     * 导航页码
     */
    private List<Integer> pageNavs;

    @Data
    public static class BrandVo {

        /**
         * 品牌ID
         */
        private Long brandId;

        /**
         * 品牌名
         */
        private String brandName;

        /**
         * 品牌图片
         */
        private String brandImg;
    }

    @Data
    public static class AttrVo {

        /**
         * 属性ID
         */
        private Long attrId;

        /**
         * 属性名
         */
        private String attrName;

        /**
         * 属性值
         */
        private List<String> attrValue;
    }

    @Data
    public static class CatalogVo {

        /**
         * 分类ID
         */
        private Long catalogId;

        /**
         * 分类名称
         */
        private String catalogName;
    }
}
