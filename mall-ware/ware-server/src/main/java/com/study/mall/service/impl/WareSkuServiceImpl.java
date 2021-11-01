package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.common.utils.R;
import com.study.mall.entity.WareSkuEntity;
import com.study.mall.feign.ISkuInfoFeignService;
import com.study.mall.mapper.WareSkuMapper;
import com.study.mall.service.IWareSkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@Service("wareSkuService")
@Transactional(rollbackFor = Exception.class)
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSkuEntity> implements IWareSkuService {

    @Resource
    private WareSkuMapper wareSkuMapper;

    @Resource
    private ISkuInfoFeignService skuInfoFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (StringUtils.isNotBlank(skuId)) {
            wrapper.eq(WareSkuEntity.SKU_ID, skuId);
        }
        String wareId = (String) params.get("wareId");
        if (StringUtils.isNotBlank(wareId)) {
            wrapper.eq(WareSkuEntity.WARE_ID, wareId);
        }
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public boolean addStock(Long skuId, Long wareId, Integer skuNum) {
        List<WareSkuEntity> wareSkuEntities = wareSkuMapper.selectList(new QueryWrapper<WareSkuEntity>().eq(WareSkuEntity.SKU_ID, skuId).eq(WareSkuEntity.WARE_ID, wareId));
        if (wareSkuEntities.isEmpty()) {
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setWareId(wareId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setStockLocked(0);
            R response = skuInfoFeignService.info(skuId);
            if (response.isOk()) {
                Map<String, Object> dataMap = (Map<String, Object>) response.getData();
                wareSkuEntity.setSkuName((String) dataMap.get("skuName"));
            }
            return save(wareSkuEntity);
        } else {
            return wareSkuMapper.addStock(skuId, wareId, skuNum) != 0;
        }
    }

}