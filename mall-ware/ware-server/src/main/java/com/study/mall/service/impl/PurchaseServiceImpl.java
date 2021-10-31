package com.study.mall.service.impl;

import com.alibaba.nacos.common.utils.Objects;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.constant.WareConstant;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.PurchaseDetailEntity;
import com.study.mall.entity.PurchaseEntity;
import com.study.mall.mapper.PurchaseMapper;
import com.study.mall.service.IPurchaseDetailService;
import com.study.mall.service.IPurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 采购信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@Service("purchaseService")
@Transactional(rollbackFor = Exception.class)
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, PurchaseEntity> implements IPurchaseService {

    @Resource
    private IPurchaseDetailService purchaseDetailService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceive(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>().eq(PurchaseEntity.STATUS, 0).or().eq(PurchaseEntity.STATUS, 1)
        );
        return new PageUtils(page);
    }

    @Override
    public boolean merge(Long purchaseId, List<Long> items) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getValue());
        purchaseEntity.setUpdateTime(LocalDateTime.now());
        if (Objects.isNull(purchaseId)) {
            purchaseEntity.setCreateTime(LocalDateTime.now());
            save(purchaseEntity);
            purchaseId = purchaseEntity.getId();
        } else {
            purchaseEntity.setId(purchaseId);
            updateById(purchaseEntity);
        }
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetailEntity> detailEntities = items.stream().map(id -> {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            detailEntity.setId(id);
            detailEntity.setPurchaseId(finalPurchaseId);
            detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getValue());
            return detailEntity;
        }).collect(Collectors.toList());
        return purchaseDetailService.updateBatchById(detailEntities);
    }

    @Override
    public boolean received(List<Long> purchaseIds) {
        List<PurchaseEntity> purchaseEntities = listByIds(purchaseIds);
        List<PurchaseEntity> entities = purchaseEntities.stream()
                .filter(entity -> entity.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getValue() ||
                        entity.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getValue())
                .peek(entity -> {
                    entity.setStatus(WareConstant.PurchaseStatusEnum.RECEIVED.getValue());
                    entity.setUpdateTime(LocalDateTime.now());
                })
                .collect(Collectors.toList());
        updateBatchById(entities);
        entities.forEach(item -> {
            List<PurchaseDetailEntity> detailEntities = purchaseDetailService.list(new QueryWrapper<PurchaseDetailEntity>().eq(PurchaseDetailEntity.PURCHASE_ID, item.getId()));
            List<PurchaseDetailEntity> updateDetails = detailEntities.stream()
                    .peek(detail -> detail.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getValue()))
                    .collect(Collectors.toList());
            purchaseDetailService.updateBatchById(updateDetails);
        });
        return true;
    }

}