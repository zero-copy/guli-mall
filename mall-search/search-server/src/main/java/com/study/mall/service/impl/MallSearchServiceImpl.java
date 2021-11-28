package com.study.mall.service.impl;

import com.alibaba.nacos.common.utils.Objects;
import com.study.mall.constant.EsConstant;
import com.study.mall.form.SearchForm;
import com.study.mall.service.IMallSearchService;
import com.study.mall.vo.SearchVo;
import org.apache.logging.log4j.util.Strings;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Harlan
 * @date 2021 11 27 下午 05:22
 */
@Service
public class MallSearchServiceImpl implements IMallSearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchVo search(SearchForm searchParam) {
        SearchVo searchVo = null;
        SearchRequest searchRequest = buildSearchRequest(searchParam);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            searchVo = buildSearchVo(searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchVo;
    }

    /**
     * 构建结果数据
     * @param searchResponse 结果集
     * @return 结果数据
     */
    private SearchVo buildSearchVo(SearchResponse searchResponse) {
        return null;
    }

    /**
     * 构建检索请求
     * @param searchParam 查询条件
     * @return 检索请求
     */
    private SearchRequest buildSearchRequest(SearchForm searchParam) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //SKU关键字检索
        if (Strings.isNotBlank(searchParam.getKeyword())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("skuTitle", searchParam.getKeyword()));
        }
        //三级分类ID检索
        if (Objects.nonNull(searchParam.getCatalog3Id())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("catalogId", searchParam.getCatalog3Id()));
        }
        //品牌ID检索
        if (Objects.nonNull(searchParam.getBrandIds()) && !searchParam.getBrandIds().isEmpty()) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("brandId", searchParam.getBrandIds()));
        }
        //库存检索
        if (Objects.nonNull(searchParam.getHasStock())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hasStock", searchParam.getHasStock() == 1));
        }
        //价格区间检索
        if (Strings.isNotBlank(searchParam.getSkuPrice())) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            String[] prices = searchParam.getSkuPrice().split("_");
            if (prices.length == 2) {
                rangeQuery.gte(prices[0]).lte(prices[1]);
            } else if (prices.length == 1) {
                if (searchParam.getSkuPrice().startsWith("_")) {
                    rangeQuery.lte(prices[0]);
                }
                if (searchParam.getSkuPrice().endsWith("_")) {
                    rangeQuery.gte(prices[0]);
                }
            }
            boolQueryBuilder.filter(rangeQuery);
        }
        //指定属性检索
        if (Objects.nonNull(searchParam.getAttrs()) && !searchParam.getAttrs().isEmpty()) {
            for (String attrStr : searchParam.getAttrs()) {
                BoolQueryBuilder nestedBoolQuery = QueryBuilders.boolQuery();
                String[] strArray = attrStr.split("_");
                //属性ID
                String attrId = strArray[0];
                //属性值
                String[] attrValues = strArray[1].split(":");
                nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                nestedBoolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", nestedBoolQuery, ScoreMode.None);
                boolQueryBuilder.filter(nestedQuery);
            }
        }
        searchSourceBuilder.query(boolQueryBuilder);
        //排序检索
        if (Strings.isNotBlank(searchParam.getSort())) {
            String sortStr = searchParam.getSort();
            String[] sortValues = sortStr.split("_");
            searchSourceBuilder.sort(sortValues[0], sortValues[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);
        }
        //分页
        searchSourceBuilder.from((searchParam.getPageNum() - 1) * EsConstant.PRODUCT_PAGE_SIZE);
        searchSourceBuilder.size(EsConstant.PRODUCT_PAGE_SIZE);
        //高亮字段
        if (Strings.isNotBlank(searchParam.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags("<b style:'color:red>");
            highlightBuilder.postTags("</b>");
            searchSourceBuilder.highlighter(highlightBuilder);
        }
        String string = searchSourceBuilder.toString();
        //聚合分析
        return new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, searchSourceBuilder);
    }
}
