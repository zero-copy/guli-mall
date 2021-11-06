package com.study.mall.mapper;

import com.study.mall.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Mapper
public interface AttrMapper extends BaseMapper<AttrEntity> {

    /**
     * 查询可检索的属性id
     *
     * @param attrIds 属性id
     * @return 属性id
     */
    List<Long> selectSearchType(@Param("attrIds") List<Long> attrIds);
}
