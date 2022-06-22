package com.study.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.constant.ProductConstant;
import com.study.mall.common.lang.dto.SkuReductionDto;
import com.study.mall.common.lang.dto.SkuStockDto;
import com.study.mall.common.lang.dto.SpuBoundsDto;
import com.study.mall.common.lang.dto.es.SkuEsDto;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.common.lang.R;
import com.study.mall.entity.*;
import com.study.mall.feign.ICouponFeignService;
import com.study.mall.feign.IEsProductFeignService;
import com.study.mall.feign.IWareSkuFeignService;
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
import java.util.*;
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

    @Resource
    private IBrandService brandService;

    @Resource
    private ICategoryService categoryService;

    @Resource
    private IWareSkuFeignService wareSkuFeignService;

    @Resource
    private IEsProductFeignService esProductFeignService;

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
        R<Object> boundsRes = couponFeignService.saveSpuBounds(spuBoundsDto);
        if (boundsRes.getCode() != 0) {
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
                    if (reductionRes.getCode() != 0) {
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

    @Override
    public boolean up(Long spuId) {
        List<SkuInfoEntity> skuInfoEntities = skuInfoService.getBySpuId(spuId);
        List<Long> skuIds = skuInfoEntities.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
        R<List<SkuStockDto>> response = wareSkuFeignService.hasStock(skuIds);
        List<SkuStockDto> skuStockDtos = response.getData();
        Map<Long, Boolean> stockMap = skuStockDtos.stream()
                .collect(Collectors.toMap(SkuStockDto::getSkuId, SkuStockDto::getHasStock));
        //查询可被检索的规格属性
        List<ProductAttrValueEntity> attrValueEntities = attrValueService.list(
                new QueryWrapper<ProductAttrValueEntity>().eq(ProductAttrValueEntity.SPU_ID, spuId)
        );
        List<Long> attrIds = attrValueEntities.stream()
                .map(ProductAttrValueEntity::getAttrId)
                .collect(Collectors.toList());
        List<Long> searchTypeIds = attrService.getSearchType(attrIds);
        Set<Long> idSet = new HashSet<>(searchTypeIds);
        List<SkuEsDto.Attrs> searchAttrs = attrValueEntities.stream()
                .filter(item -> idSet.contains(item.getAttrId()))
                .map(item -> BeanUtil.copyProperties(item, SkuEsDto.Attrs.class))
                .collect(Collectors.toList());
        List<SkuEsDto> skuEsDtos = skuInfoEntities.stream().map(sku -> {
            SkuEsDto skuEsDto = BeanUtil.copyProperties(sku, SkuEsDto.class);
            skuEsDto.setSkuPrice(sku.getPrice());
            skuEsDto.setSkuImg(sku.getSkuDefaultImg());
            //查询是否有库存
            skuEsDto.setHasStock(stockMap.get(sku.getSkuId()));
            //热度评分
            skuEsDto.setHotScore(0L);
            BrandEntity brandEntity = brandService.getById(sku.getBrandId());
            skuEsDto.setBrandName(brandEntity.getName());
            skuEsDto.setBrandImg(brandEntity.getLogo());
            CategoryEntity categoryEntity = categoryService.getById(sku.getCatalogId());
            skuEsDto.setCatalogId(sku.getCatalogId());
            skuEsDto.setCatalogName(categoryEntity.getName());
            skuEsDto.setAttrs(searchAttrs);
            return skuEsDto;
        }).collect(Collectors.toList());
        //发送Es
        R<Object> r = esProductFeignService.productStatusUp(skuEsDtos);
        boolean isSuccess = r.getCode() == 0;
        if (isSuccess) {
            return baseMapper.updateStatus(spuId, ProductConstant.StatusEnum.UP.getValue()) != 0;
        } else {
            log.error(r.getMsg());
            return false;
        }
    }

    @Override
    public SpuInfoEntity getBySkuId(Long id) {
        SkuInfoEntity skuInfo = skuInfoService.getById(id);
        return getById(skuInfo.getSpuId());
    }

}