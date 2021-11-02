package com.study.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.mall.entity.SpuInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfoEntity> {

    /**
     * 更新Spu状态
     *
     * @param spuId  spuid
     * @param status 状态
     * @return 影响行数
     */
    long updateStatus(@Param("spuId") Long spuId, @Param("status") int status);
}
