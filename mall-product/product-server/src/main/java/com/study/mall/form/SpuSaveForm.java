package com.study.mall.form;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2021-10-30 17:47:42
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class SpuSaveForm {

    private String spuName;

    private String spuDescription;

    private Long catalogId;

    private Long brandId;

    private BigDecimal weight;

    private Integer publishStatus;

    private List<String> decript;

    private List<String> images;

    private SpuBoundsForm bounds;

    private List<SpuBaseAttrsForm> baseAttrs;

    private List<SkuSkusForm> skus;

}