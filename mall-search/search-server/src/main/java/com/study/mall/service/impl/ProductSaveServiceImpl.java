package com.study.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.study.mall.common.lang.dto.es.SkuEsDto;
import com.study.mall.config.es.ElasticSearchConfig;
import com.study.mall.constant.EsConstant;
import com.study.mall.service.IProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Harlan
 * @date 2021 11 03 上午 01:33
 */
@Slf4j
@Service
public class ProductSaveServiceImpl implements IProductSaveService {

    @Resource
    private RestHighLevelClient restClient;

    @Override
    public boolean statusUp(List<SkuEsDto> skuEsDtos) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsDto skuEsDto : skuEsDtos) {
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsDto.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(skuEsDto), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse responses = restClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);
        boolean hasFailures = responses.hasFailures();
        if (hasFailures) {
            List<String> failedIds = Arrays.stream(responses.getItems())
                    .map(BulkItemResponse::getId)
                    .collect(Collectors.toList());
            log.error("商品保存ElasticSearch错误: {}", failedIds);
        }
        return hasFailures;
    }
}
