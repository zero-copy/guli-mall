package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.PurchaseDetailEntity;
import com.study.mall.mapper.PurchaseDetailMapper;
import com.study.mall.service.IPurchaseDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetailEntity> implements IPurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(w -> w.eq(PurchaseDetailEntity.PURCHASE_ID, key)
                    .or().eq(PurchaseDetailEntity.SKU_ID, key));
        }
        String status = (String) params.get("status");
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(PurchaseDetailEntity.STATUS, status);
        }
        String wareId = (String) params.get("wareId");
        if (StringUtils.isNotBlank(wareId)) {
            wrapper.eq(PurchaseDetailEntity.WARE_ID, wareId);
        }
        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

}