package com.study.mall.controller;

import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;
import com.study.mall.entity.SpuInfoEntity;
import com.study.mall.form.SpuSaveForm;
import com.study.mall.service.ISpuInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * spu信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {

    @Resource
    private ISpuInfoService spuInfoService;

    @PostMapping("/{spuId}/up")
    public R spuUp(@PathVariable Long spuId) {
        spuInfoService.up(spuId);
        return R.ok();
    }

    @GetMapping("/skuId/{id}")
    public R<SpuInfoEntity> getInfoBySkuId(@PathVariable Long id) {
        SpuInfoEntity spuInfo = spuInfoService.getBySkuId(id);
        return R.ok(spuInfo);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    //product:spuinfo:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = spuInfoService.queryPageByCondition(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //product:spuinfo:info
    public R info(@PathVariable("id") Long id) {
        SpuInfoEntity spuInfo = spuInfoService.getById(id);
        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //product:spuinfo:save
    public R save(@RequestBody SpuSaveForm spuInfoForm) {
        spuInfoService.saveInfo(spuInfoForm);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //product:spuinfo:update
    public R update(@RequestBody SpuInfoEntity spuInfo) {
        spuInfoService.updateById(spuInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:spuinfo:delete")
    public R delete(@RequestBody Long[] ids) {
        spuInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
