package com.study.mall.ware.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.ware.entity.WareOrderTaskDetailEntity;
import com.study.mall.ware.service.IWareOrderTaskDetailService;
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
@RequestMapping("ware/wareordertaskdetail")
public class WareOrderTaskDetailController {

    @Resource
    private IWareOrderTaskDetailService wareOrderTaskDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //ware:wareordertaskdetail:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareOrderTaskDetailService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //ware:wareordertaskdetail:info
    public R info(@PathVariable("id") Long id) {
            WareOrderTaskDetailEntity wareOrderTaskDetail = wareOrderTaskDetailService.getById(id);
        return R.ok().put("wareOrderTaskDetail", wareOrderTaskDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //ware:wareordertaskdetail:save
    public R save(@RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail) {
            wareOrderTaskDetailService.save(wareOrderTaskDetail);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //ware:wareordertaskdetail:update
    public R update(@RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail) {
            wareOrderTaskDetailService.updateById(wareOrderTaskDetail);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareordertaskdetail:delete")
    public R delete(@RequestBody Long[] ids) {
            wareOrderTaskDetailService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
