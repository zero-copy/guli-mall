package com.study.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.mall.entity.AttrGroupEntity;
import com.study.mall.vo.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 属性分组
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@Mapper
public interface AttrGroupMapper extends BaseMapper<AttrGroupEntity> {

    List<SpuItemAttrGroupVo> getWithAttrBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
