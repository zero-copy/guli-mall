package com.study.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.product.entity.AttrEntity;
import com.study.mall.product.vo.AttrReqVo;
import com.study.mall.product.vo.AttrRespVo;
import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
public interface IAttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存属性
     * @param groupId 属性分组
     * @param attr 属性信息
     * @return 是否成功
     */
    boolean save(Long groupId, AttrEntity attr);

    /**
     * 查询基础属性
     * @param attrType 属性类别
     * @param catelogId 分类Id
     * @param params 参数
     * @return 分页信息
     */
    PageUtils queryAttrPage(Integer attrType, Long catelogId, Map<String, Object> params);

    /**
     * 查询详细信息
     * @param attrId 属性ID
     * @return 详细信息
     */
    AttrRespVo getDetailById(Long attrId);

    /**
     * 更新详细信息
     * @param attr 属性
     * @return 是否成功
     */
    boolean updateDetail(AttrReqVo attr);

    /**
     * 获取分组中所有属性
     * @param attrGroupId 分组ID
     * @return 所有属性
     */
    List<AttrEntity> getGroupAttr(Long attrGroupId);
}

