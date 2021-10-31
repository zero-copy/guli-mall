package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
    
/**
 * 仓库信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@Data
@TableName("wms_ware_info")
public class WareInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * 仓库名
    */
    private String name;

    /**
    * 仓库地址
    */
    private String address;

    /**
    * 区域编码
    */
    private String areacode;

    
    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ADDRESS = "address";

    public static final String AREACODE = "areacode";

    
}
