package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.SpuInfoEntity;
import com.study.mall.form.SpuSaveForm;

import java.util.Map;

/**
 * spu信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
public interface ISpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存Spu信息
     * @param spuInfoForm 表单提交信息
     * @return 是否成功
     */
    boolean saveInfo(SpuSaveForm spuInfoForm);

    /**
     * 通过条件检索Spu信息
     * @param params 查询条件
     * @return 分页信息
     */
    PageUtils queryPageByCondition(Map<String, Object> params);
}

