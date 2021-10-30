package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.BrandEntity;
import java.util.Map;

/**
 * 品牌
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
public interface IBrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 更新品牌信息，同步数据
     * @param brand 品牌
     * @return 是否成功
     */
    boolean updateDetail(BrandEntity brand);
}

