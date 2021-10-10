package com.study.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Data
@TableName("undo_log")
public class UndoLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @TableId
    private Long id;

    /**
    * 
    */
    private Long branchId;

    /**
    * 
    */
    private String xid;

    /**
    * 
    */
    private String context;

    /**
    * 
    */
    private Byte[] rollbackInfo;

    /**
    * 
    */
    private Integer logStatus;

    /**
    * 
    */
    private LocalDateTime logCreated;

    /**
    * 
    */
    private LocalDateTime logModified;

    /**
    * 
    */
    private String ext;

        
    private static final String ID = "id";

    private static final String BRANCH_ID = "branch_id";

    private static final String XID = "xid";

    private static final String CONTEXT = "context";

    private static final String ROLLBACK_INFO = "rollback_info";

    private static final String LOG_STATUS = "log_status";

    private static final String LOG_CREATED = "log_created";

    private static final String LOG_MODIFIED = "log_modified";

    private static final String EXT = "ext";

    
}
