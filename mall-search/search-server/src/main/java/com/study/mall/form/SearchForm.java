package com.study.mall.form;

import lombok.Data;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 27 下午 05:17
 */
@Data
public class SearchForm {

    /**
     * 检索词
     */
    private String keyword;

    /**
     * 三级分类Id
     */
    private Long catalog3Id;

    /**
     * 排序条件
     */
    private String sort;

    /**
     * 是否有货 0 无货 : 1 有货
     */
    private Integer hasStock;

    /**
     * 价格区间
     */
    private String skuPrice;

    /**
     * 品牌Id
     */
    private List<Long> brandIds;

    /**
     * 属性
     */
    private List<String> attrs;

    /**
     * 页码：默认页码 1
     */
    private Integer pageNum = 1;

    /**
     * 查询条件
     */
    private String _queryString;

}
