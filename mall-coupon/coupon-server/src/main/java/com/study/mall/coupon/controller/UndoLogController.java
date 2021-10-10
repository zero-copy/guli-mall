package com.study.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.coupon.entity.UndoLogEntity;
import com.study.mall.coupon.service.IUndoLogService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * 
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/undolog")
public class UndoLogController {

    @Resource
    private IUndoLogService undoLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:undolog:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = undoLogService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:undolog:info
    public R info(@PathVariable("id") Long id) {
            UndoLogEntity undoLog = undoLogService.getById(id);
        return R.ok().put("undoLog", undoLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:undolog:save
    public R save(@RequestBody UndoLogEntity undoLog) {
            undoLogService.save(undoLog);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:undolog:update
    public R update(@RequestBody UndoLogEntity undoLog) {
            undoLogService.updateById(undoLog);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:undolog:delete")
    public R delete(@RequestBody Long[] ids) {
            undoLogService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
