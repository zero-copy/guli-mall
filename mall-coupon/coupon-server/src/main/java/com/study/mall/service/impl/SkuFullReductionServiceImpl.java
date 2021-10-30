package com.study.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.dto.MemberPriceDto;
import com.study.mall.common.dto.SkuReductionDto;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.MemberPriceEntity;
import com.study.mall.entity.SkuFullReductionEntity;
import com.study.mall.entity.SkuLadderEntity;
import com.study.mall.mapper.SkuFullReductionMapper;
import com.study.mall.service.IMemberPriceService;
import com.study.mall.service.ISkuFullReductionService;
import com.study.mall.service.ISkuLadderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品满减信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReductionEntity> implements ISkuFullReductionService {

    @Resource
    private ISkuLadderService skuLadderService;

    @Resource
    private IMemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public boolean saveSkuReduction(SkuReductionDto reductionDto) {
        //保存满减 会员价
        SkuLadderEntity skuLadderEntity = BeanUtil.copyProperties(reductionDto, SkuLadderEntity.class);
        skuLadderEntity.setAddOther(reductionDto.getCountStatus());
        SkuFullReductionEntity fullReductionEntity = BeanUtil.copyProperties(reductionDto, SkuFullReductionEntity.class);
        if (reductionDto.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }
        if (reductionDto.getFullPrice().compareTo(new BigDecimal(0)) > 0) {
            save(fullReductionEntity);
        }
        List<MemberPriceDto> memberPrices = reductionDto.getMemberPrice();
        List<MemberPriceEntity> memberPriceEntities = memberPrices.stream()
                .filter(prices -> prices.getPrice().compareTo(new BigDecimal(0)) > 0)
                .map(price -> {
                    MemberPriceEntity priceEntity = new MemberPriceEntity();
                    priceEntity.setSkuId(reductionDto.getSkuId());
                    priceEntity.setMemberLevelId(price.getId());
                    priceEntity.setMemberLevelName(price.getName());
                    priceEntity.setMemberPrice(price.getPrice());
                    priceEntity.setAddOther(1);
                    return priceEntity;
        }).collect(Collectors.toList());
        return memberPriceService.saveBatch(memberPriceEntities);
    }

}