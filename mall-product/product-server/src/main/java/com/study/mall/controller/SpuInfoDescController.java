package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.SpuInfoDescEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.ISpuInfoDescService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * spu信息介绍
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@RestController
@RequestMapping("product/spuinfodesc")
public class SpuInfoDescController {

    @Resource
    private ISpuInfoDescService spuInfoDescService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:spuinfodesc:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = spuInfoDescService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{spuId}")
    //product:spuinfodesc:info
    public R info(@PathVariable("spuId") Long spuId) {
            SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(spuId);
        return R.ok().put("spuInfoDesc", spuInfoDesc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:spuinfodesc:save
    public R save(@RequestBody SpuInfoDescEntity spuInfoDesc) {
            spuInfoDescService.save(spuInfoDesc);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:spuinfodesc:update
    public R update(@RequestBody SpuInfoDescEntity spuInfoDesc) {
            spuInfoDescService.updateById(spuInfoDesc);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:spuinfodesc:delete")
    public R delete(@RequestBody Long[] spuIds) {
            spuInfoDescService.removeByIds(Arrays.asList(spuIds));
        return R.ok();
    }

}
