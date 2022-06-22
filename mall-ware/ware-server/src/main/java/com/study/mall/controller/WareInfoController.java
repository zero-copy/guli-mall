package com.study.mall.controller;

import com.study.mall.common.lang.R;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.WareInfoEntity;
import com.study.mall.service.IWareInfoService;
import com.study.mall.vo.FareVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


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
    @GetMapping("/list")
    //ware:wareinfo:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //ware:wareinfo:info
    public R info(@PathVariable("id") Long id) {
        WareInfoEntity wareInfo = wareInfoService.getById(id);
        return R.ok().put("wareInfo", wareInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //ware:wareinfo:save
    public R save(@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.save(wareInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //ware:wareinfo:update
    public R update(@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.updateById(wareInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("ware:wareinfo:delete")
    public R delete(@RequestBody Long[] ids) {
        wareInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/fare")
    public R<FareVo> getFare(@RequestParam Long addrId) {
        FareVo fare = wareInfoService.getFare(addrId);
        return R.ok(fare);
    }

}
