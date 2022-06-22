package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.lang.R;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.dto.MemberAddressDto;
import com.study.mall.entity.WareInfoEntity;
import com.study.mall.feign.IMemberAddressFeignService;
import com.study.mall.mapper.WareInfoMapper;
import com.study.mall.service.IWareInfoService;
import com.study.mall.vo.FareVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * 仓库信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@Service("wareInfoService")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfoEntity> implements IWareInfoService {

    @Resource
    private IMemberAddressFeignService memberAddressFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.eq(WareInfoEntity.ID, key)
                    .or().like(WareInfoEntity.NAME, key)
                    .or().like(WareInfoEntity.ADDRESS, key)
                    .or().like(WareInfoEntity.AREACODE, key);
        }
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public FareVo getFare(Long addrId) {
        R<MemberAddressDto> res = memberAddressFeignService.info(addrId);
        FareVo fareVo = new FareVo();
        if (res.getCode() == 0) {
            MemberAddressDto data = res.getData();
            if (Objects.nonNull(data)) {
                String numStr = data.getPhone().substring(data.getPhone().length() - 2);
                fareVo.setFare(new BigDecimal(numStr));
                fareVo.setAddress(data);
                return fareVo;
            }
        }
        MemberAddressDto addressDto = new MemberAddressDto();
        addressDto.setDetailAddress("请设置地址");
        addressDto.setName("请设置地址");
        fareVo.setFare(BigDecimal.TEN);
        fareVo.setAddress(addressDto);
        return fareVo;
    }

}