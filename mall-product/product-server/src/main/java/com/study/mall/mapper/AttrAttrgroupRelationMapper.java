package com.study.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.mall.entity.AttrAttrgroupRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Mapper
public interface AttrAttrgroupRelationMapper extends BaseMapper<AttrAttrgroupRelationEntity> {

    /**
     * 批量删除关系
     * @param relations 关系
     * @return 受影响行
     */
    int deleteBatchRelation(@Param("relations") List<AttrAttrgroupRelationEntity> relations);
}
