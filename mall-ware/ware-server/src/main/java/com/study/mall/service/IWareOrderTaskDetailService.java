package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.WareOrderTaskDetailEntity;
import java.util.Map;

/**
 * 库存工作单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
public interface IWareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

