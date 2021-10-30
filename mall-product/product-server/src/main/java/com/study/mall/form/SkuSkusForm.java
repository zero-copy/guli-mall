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
public class SkuSkusForm {

    private List<SkuAttrForm> attr;

    private String skuName;

    private BigDecimal price;

    private String skuTitle;

    private String skuSubtitle;

    private List<ImagesForm> images;

    private List<String> descar;

    private Integer fullCount;

    private BigDecimal discount;

    private Integer countStatus;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;

    private Integer priceStatus;

    private List<SpuMemberPriceForm> memberPrice;
}