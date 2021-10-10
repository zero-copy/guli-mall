package com.study.mall.ware.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.ware.entity.WareInfoEntity;
import com.study.mall.ware.service.IWareInfoService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * 仓库信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@RestController
@RequestMapping("ware/wareinfo")
public class WareInfoController {

    @Resource
    private IWareInfoService wareInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //ware:wareinfo:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //ware:wareinfo:info
    public R info(@PathVariable("id") Long id) {
            WareInfoEntity wareInfo = wareInfoService.getById(id);
        return R.ok().put("wareInfo", wareInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //ware:wareinfo:save
    public R save(@RequestBody WareInfoEntity wareInfo) {
            wareInfoService.save(wareInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //ware:wareinfo:update
    public R update(@RequestBody WareInfoEntity wareInfo) {
            wareInfoService.updateById(wareInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareinfo:delete")
    public R delete(@RequestBody Long[] ids) {
            wareInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
