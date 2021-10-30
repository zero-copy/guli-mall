package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.WareOrderTaskEntity;
import com.study.mall.service.IWareOrderTaskService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 库存工作单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@RestController
@RequestMapping("ware/wareordertask")
public class WareOrderTaskController {

    @Resource
    private IWareOrderTaskService wareOrderTaskService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //ware:wareordertask:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareOrderTaskService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //ware:wareordertask:info
    public R info(@PathVariable("id") Long id) {
            WareOrderTaskEntity wareOrderTask = wareOrderTaskService.getById(id);
        return R.ok().put("wareOrderTask", wareOrderTask);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //ware:wareordertask:save
    public R save(@RequestBody WareOrderTaskEntity wareOrderTask) {
            wareOrderTaskService.save(wareOrderTask);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //ware:wareordertask:update
    public R update(@RequestBody WareOrderTaskEntity wareOrderTask) {
            wareOrderTaskService.updateById(wareOrderTask);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareordertask:delete")
    public R delete(@RequestBody Long[] ids) {
            wareOrderTaskService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
