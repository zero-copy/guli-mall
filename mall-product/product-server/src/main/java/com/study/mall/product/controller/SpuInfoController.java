package com.study.mall.product.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.product.entity.SpuInfoEntity;
import com.study.mall.product.service.ISpuInfoService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


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

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:spuinfo:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = spuInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //product:spuinfo:info
    public R info(@PathVariable("id") Long id) {
            SpuInfoEntity spuInfo = spuInfoService.getById(id);
        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:spuinfo:save
    public R save(@RequestBody SpuInfoEntity spuInfo) {
            spuInfoService.save(spuInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:spuinfo:update
    public R update(@RequestBody SpuInfoEntity spuInfo) {
            spuInfoService.updateById(spuInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:spuinfo:delete")
    public R delete(@RequestBody Long[] ids) {
            spuInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
