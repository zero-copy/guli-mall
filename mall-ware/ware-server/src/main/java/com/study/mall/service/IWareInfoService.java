package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.WareInfoEntity;
import com.study.mall.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
public interface IWareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过收货地址计算运费
     * @param addrId 收货地址ID
     * @return 运费
     */
    FareVo getFare(Long addrId);
}

