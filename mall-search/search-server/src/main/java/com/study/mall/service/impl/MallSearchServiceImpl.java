package com.study.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.Objects;
import com.study.mall.common.lang.R;
import com.study.mall.common.lang.dto.es.SkuEsDto;
import com.study.mall.constant.EsConstant;
import com.study.mall.dto.AttrDto;
import com.study.mall.feign.IProductFeignService;
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
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Harlan
 * @date 2021 11 27 ?????? 05:22
 */
@Service
public class MallSearchServiceImpl implements IMallSearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private IProductFeignService productFeignService;

    @Override
    public SearchVo search(SearchForm searchParam) {
        SearchVo searchVo = null;
        SearchRequest searchRequest = buildSearchRequest(searchParam);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            searchVo = buildSearchVo(searchResponse, searchParam);
            searchVo.setPageNum(searchParam.getPageNum());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchVo;
    }

    /**
     * ??????????????????
     * @param searchResponse ?????????
     * @return ????????????
     */
    private SearchVo buildSearchVo(SearchResponse searchResponse, SearchForm searchParam) {
        SearchHits hits = searchResponse.getHits();
        List<SkuEsDto> skuEsDtos = new ArrayList<>();
        if (Objects.nonNull(hits.getHits())) {
            //??????????????????
            for (SearchHit hit : hits.getHits()) {
                String source = hit.getSourceAsString();
                SkuEsDto skuEsDto = JSON.parseObject(source, SkuEsDto.class);
                HighlightField skuTitle = hit.getHighlightFields().get("skuTitle");
                if (Objects.nonNull(skuTitle) && skuTitle.getFragments().length > 0) {
                    skuEsDto.setSkuTitle(skuTitle.getFragments()[0].string());
                }
                skuEsDtos.add(skuEsDto);
            }
        }
        SearchVo searchVo = new SearchVo();
        //??????????????????
        searchVo.setProducts(skuEsDtos);
        //????????????
        Aggregations aggregations = searchResponse.getAggregations();
        //??????????????????????????????
        ParsedNested attrAgg = aggregations.get("attr_agg");
        ParsedLongTerms attrIdAgg = attrAgg.getAggregations().get("attr_id_agg");
        List<SearchVo.AttrVo> attrVos = new ArrayList<>();
        for (Terms.Bucket bucket : attrIdAgg.getBuckets()) {
            SearchVo.AttrVo attrVo = new SearchVo.AttrVo();
            long attrId = bucket.getKeyAsNumber().longValue();
            String attrName = ((ParsedStringTerms) bucket.getAggregations().get("attr_name_agg")).getBuckets().get(0).getKeyAsString();
            List<String> attrValues = ((ParsedStringTerms) bucket.getAggregations().get("attr_value_agg")).getBuckets().stream()
                    .map(MultiBucketsAggregation.Bucket::getKeyAsString).collect(Collectors.toList());
            attrVo.setAttrId(attrId);
            attrVo.setAttrName(attrName);
            attrVo.setAttrValue(attrValues);
            attrVos.add(attrVo);
        }
        searchVo.setAttrs(attrVos);
        //????????????????????????
        List<SearchVo.BrandVo> brandVos = new ArrayList<>();
        ParsedLongTerms brandAgg = aggregations.get("brand_agg");
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            SearchVo.BrandVo brandVo = new SearchVo.BrandVo();
            long brandId = bucket.getKeyAsNumber().longValue();
            String brandImg = ((ParsedStringTerms) bucket.getAggregations().get("brand_img_agg")).getBuckets().get(0).getKeyAsString();
            String brandName = ((ParsedStringTerms) bucket.getAggregations().get("brand_name_agg")).getBuckets().get(0).getKeyAsString();
            brandVo.setBrandId(brandId);
            brandVo.setBrandImg(brandImg);
            brandVo.setBrandName(brandName);
            brandVos.add(brandVo);
        }
        searchVo.setBrands(brandVos);
        //????????????????????????
        ParsedLongTerms catalogAgg = aggregations.get("catalog_agg");
        List<SearchVo.CatalogVo> catalogVos = new ArrayList<>();
        for (Terms.Bucket bucket : catalogAgg.getBuckets()) {
            SearchVo.CatalogVo catalogVo = new SearchVo.CatalogVo();
            String catalogId = bucket.getKeyAsString();
            catalogVo.setCatalogId(Long.parseLong(catalogId));
            ParsedStringTerms catalogNameAgg = bucket.getAggregations().get("catalog_name_agg");
            String catelogName = catalogNameAgg.getBuckets().get(0).getKeyAsString();
            catalogVo.setCatalogName(catelogName);
            catalogVos.add(catalogVo);
        }
        searchVo.setCatalogs(catalogVos);
        //????????????
        long total = hits.getTotalHits().value;
        long pageSize = total % EsConstant.PRODUCT_PAGE_SIZE == 0 ? total / EsConstant.PRODUCT_PAGE_SIZE : total / EsConstant.PRODUCT_PAGE_SIZE + 1;
        searchVo.setTotal(total);
        searchVo.setTotalPages(pageSize);
        List<Integer> pageNavs = new ArrayList<>();
        for (int i = 1; i <= pageSize; i++) {
            pageNavs.add(i);
        }
        searchVo.setPageNavs(pageNavs);
        //??????????????????
        if (Objects.nonNull(searchParam.getAttrs()) && !searchParam.getAttrs().isEmpty()) {
            List<SearchVo.NavVo> navVos = searchParam.getAttrs().stream().map(attr -> {
                SearchVo.NavVo navVo = new SearchVo.NavVo();
                String[] strings = attr.split("_");
                R<AttrDto> info = productFeignService.getInfo(Long.parseLong(strings[0]));
                if (info.getCode() == 0) {
                    AttrDto data = info.getData();
                    navVo.setNavName(data.getAttrName());
                } else {
                    navVo.setNavName(strings[0]);
                }
                String link = searchParam.get_queryString().replace("&attrs=" + attr, "");
                navVo.setLink("http://search.gulimall.com/list.html?" + link);
                navVo.setNavValue(strings[1]);
                return navVo;
            }).collect(Collectors.toList());
            searchVo.setNavs(navVos);
        }
        return searchVo;
    }

    /**
     * ??????????????????
     * @param searchParam ????????????
     * @return ????????????
     */
    private SearchRequest buildSearchRequest(SearchForm searchParam) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //SKU???????????????
        if (Strings.isNotBlank(searchParam.getKeyword())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("skuTitle", searchParam.getKeyword()));
        }
        //????????????ID??????
        if (Objects.nonNull(searchParam.getCatalog3Id())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("catalogId", searchParam.getCatalog3Id()));
        }
        //??????ID??????
        if (Objects.nonNull(searchParam.getBrandIds()) && !searchParam.getBrandIds().isEmpty()) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("brandId", searchParam.getBrandIds()));
        }
        //????????????
        if (Objects.nonNull(searchParam.getHasStock())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hasStock", searchParam.getHasStock() == 1));
        }
        //??????????????????
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
        //??????????????????
        if (Objects.nonNull(searchParam.getAttrs()) && !searchParam.getAttrs().isEmpty()) {
            for (String attrStr : searchParam.getAttrs()) {
                BoolQueryBuilder nestedBoolQuery = QueryBuilders.boolQuery();
                String[] strArray = attrStr.split("_");
                //??????ID
                String attrId = strArray[0];
                //?????????
                String[] attrValues = strArray[1].split(":");
                nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                nestedBoolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", nestedBoolQuery, ScoreMode.None);
                boolQueryBuilder.filter(nestedQuery);
            }
        }
        searchSourceBuilder.query(boolQueryBuilder);
        //????????????
        if (Strings.isNotBlank(searchParam.getSort())) {
            String sortStr = searchParam.getSort();
            String[] sortValues = sortStr.split("_");
            searchSourceBuilder.sort(sortValues[0], sortValues[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);
        }
        //??????
        searchSourceBuilder.from((searchParam.getPageNum() - 1) * EsConstant.PRODUCT_PAGE_SIZE);
        searchSourceBuilder.size(EsConstant.PRODUCT_PAGE_SIZE);
        //????????????
        if (Strings.isNotBlank(searchParam.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");
            searchSourceBuilder.highlighter(highlightBuilder);
        }
        //????????????
        //????????????
        TermsAggregationBuilder brandAgg = AggregationBuilders.terms("brand_agg");
        brandAgg.field("brandId");
        //?????????????????????
        brandAgg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        brandAgg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        //????????????
        TermsAggregationBuilder catalogAgg = AggregationBuilders.terms("catalog_agg").field("catalogId").size(20);
        //???????????????
        catalogAgg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        //????????????
        NestedAggregationBuilder attrAgg = AggregationBuilders.nested("attr_agg", "attrs");
        //?????????????????????
        TermsAggregationBuilder attrIdAgg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId");
        attrIdAgg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        attrIdAgg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(50));
        attrAgg.subAggregation(attrIdAgg);
        //????????????
        searchSourceBuilder.aggregation(brandAgg);
        searchSourceBuilder.aggregation(catalogAgg);
        searchSourceBuilder.aggregation(attrAgg);
        return new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, searchSourceBuilder);
    }
}
