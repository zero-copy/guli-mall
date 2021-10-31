package com.study.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.dto.SkuReductionDto;
import com.study.mall.common.dto.SpuBoundsDto;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.common.utils.R;
import com.study.mall.entity.*;
import com.study.mall.feign.ICouponFeignService;
import com.study.mall.form.*;
import com.study.mall.mapper.SpuInfoMapper;
import com.study.mall.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * spu信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Slf4j
@Service("spuInfoService")
@Transactional(rollbackFor = Exception.class)
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfoEntity> implements ISpuInfoService {

    @Resource
    private ISpuInfoDescService spuInfoDescService;

    @Resource
    private ISpuImagesService imagesService;

    @Resource
    private IProductAttrValueService attrValueService;

    @Resource
    private ISkuInfoService skuInfoService;

    @Resource
    private IAttrService attrService;

    @Resource
    private ISkuImagesService skuImagesService;

    @Resource
    private ISkuSaleAttrValueService saleAttrValueService;

    @Resource
    private ICouponFeignService couponFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public boolean saveInfo(SpuSaveForm spuInfoForm) {
        //保存Spu基本信息
        SpuInfoEntity spuInfo = BeanUtil.copyProperties(spuInfoForm, SpuInfoEntity.class);
        spuInfo.setCreateTime(LocalDateTime.now());
        spuInfo.setUpdateTime(LocalDateTime.now());
        save(spuInfo);
        //保存Spu描述图片
        List<String> decriptList = spuInfoForm.getDecript();
        SpuInfoDescEntity infoDescEntity = new SpuInfoDescEntity();
        infoDescEntity.setSpuId(spuInfo.getId());
        infoDescEntity.setDecript(String.join(",", decriptList));
        spuInfoDescService.save(infoDescEntity);
        //保存Spu图片集
        List<String> spuImages = spuInfoForm.getImages();
        List<SpuImagesEntity> imagesEntities = spuImages.stream()
                .filter(StringUtils::isNotBlank)
                .map(images -> {
                    SpuImagesEntity imagesEntity = new SpuImagesEntity();
                    imagesEntity.setSpuId(spuInfo.getId());
                    imagesEntity.setImgUrl(images);
                    return imagesEntity;
                }).collect(Collectors.toList());
        imagesService.saveBatch(imagesEntities);
        //保存Spu规格参数
        List<SpuBaseAttrsForm> baseAttrsForms = spuInfoForm.getBaseAttrs();
        List<ProductAttrValueEntity> attrValueEntities = baseAttrsForms.stream().map(attrForm -> {
            ProductAttrValueEntity attrValueEntity = new ProductAttrValueEntity();
            AttrEntity attrEntity = attrService.getById(attrForm.getAttrId());
            if (Objects.nonNull(attrEntity)) {
                attrValueEntity.setAttrName(attrEntity.getAttrName());
            }
            attrValueEntity.setAttrId(attrForm.getAttrId());
            attrValueEntity.setAttrValue(attrForm.getAttrValues());
            attrValueEntity.setQuickShow(attrForm.getShowDesc());
            attrValueEntity.setSpuId(spuInfo.getId());
            return attrValueEntity;
        }).collect(Collectors.toList());
        attrValueService.saveBatch(attrValueEntities);
        //保存Spu积分信息
        SpuBoundsForm boundsForm = spuInfoForm.getBounds();
        SpuBoundsDto spuBoundsDto = BeanUtil.copyProperties(boundsForm, SpuBoundsDto.class);
        spuBoundsDto.setSpuId(spuInfo.getId());
        R boundsRes = couponFeignService.saveSpuBounds(spuBoundsDto);
        if (!boundsRes.isOk()) {
            log.error("远程保存优惠信息失败");
        }
        //保存Sku基本信息
        List<SkuSkusForm> skuForms = spuInfoForm.getSkus();
        if (!skuForms.isEmpty()) {
            skuForms.forEach(form -> {
                String defaultImg = null;
                for (ImagesForm image : form.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = BeanUtil.copyProperties(form, SkuInfoEntity.class);
                skuInfoEntity.setBrandId(spuInfo.getBrandId());
                skuInfoEntity.setCatalogId(spuInfo.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(spuInfo.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                skuInfoService.save(skuInfoEntity);
                //保存Sku图片信息
                List<SkuImagesEntity> skuImagesEntities = form.getImages().stream()
                        .filter(img -> StringUtils.isNotBlank(img.getImgUrl()))
                        .map(img -> {
                    SkuImagesEntity imagesEntity = new SkuImagesEntity();
                    imagesEntity.setSkuId(skuInfoEntity.getSkuId());
                    imagesEntity.setImgUrl(img.getImgUrl());
                    imagesEntity.setDefaultImg(img.getDefaultImg());
                    return imagesEntity;
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(skuImagesEntities);
                //保存Sku销售属性信息
                List<SkuAttrForm> attrForms = form.getAttr();
                List<SkuSaleAttrValueEntity> saleAttrValueEntities = attrForms.stream().map(attrForm -> {
                    SkuSaleAttrValueEntity saleAttrValueEntity = BeanUtil.copyProperties(attrForm, SkuSaleAttrValueEntity.class);
                    saleAttrValueEntity.setSkuId(skuInfoEntity.getSkuId());
                    return saleAttrValueEntity;
                }).collect(Collectors.toList());
                saleAttrValueService.saveBatch(saleAttrValueEntities);
                //保存Sku优惠信息
                SkuReductionDto reductionDto = BeanUtil.copyProperties(form, SkuReductionDto.class);
                reductionDto.setSkuId(skuInfoEntity.getSkuId());
                if (reductionDto.getFullCount() <= 0 || reductionDto.getFullPrice().compareTo(new BigDecimal(0)) > 0) {
                    R reductionRes = couponFeignService.saveSkuReduction(reductionDto);
                    if (!reductionRes.isOk()) {
                        log.error("远程保存Spu积分信息失败");
                    }
                }
            });
        }
        return true;
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(w -> w.eq(SpuInfoEntity.ID, key))
                    .or().like(SpuInfoEntity.SPU_NAME, key);
        }
        String status = (String) params.get("status");
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(SpuInfoEntity.PUBLISH_STATUS, status);
        }
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId)) {
            wrapper.eq(SpuInfoEntity.BRAND_ID, brandId);
        }
        String catelogId = (String) params.get("catelogId");
        if (StringUtils.isNotBlank(catelogId)) {
            wrapper.eq(SpuInfoEntity.CATALOG_ID, catelogId);
        }
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

}