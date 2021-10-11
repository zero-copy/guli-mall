package com.study.mall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.common.utils.PageUtils;
import com.study.common.utils.Query;
import com.study.mall.member.mapper.MemberReceiveAddressMapper;
import com.study.mall.member.entity.MemberReceiveAddressEntity;
import com.study.mall.member.service.IMemberReceiveAddressService;

/**
 * 会员收货地址
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressMapper, MemberReceiveAddressEntity> implements IMemberReceiveAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberReceiveAddressEntity> page = this.page(
                new Query<MemberReceiveAddressEntity>().getPage(params),
                new QueryWrapper<MemberReceiveAddressEntity>()
        );
        return new PageUtils(page);
    }

}