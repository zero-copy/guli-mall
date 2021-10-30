package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
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

    
    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String ADDRESS = "address";

    private static final String AREACODE = "areacode";

    
}
