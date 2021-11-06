package com.study.mall.controller;

import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;
import com.study.mall.entity.WareSkuEntity;
import com.study.mall.service.IWareSkuService;
import com.study.mall.common.lang.dto.SkuStockDto;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品库存
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:19
 */
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {

    @Resource
    private IWareSkuService wareSkuService;

    /**
     * 查询是否有库存
     */
    @PostMapping("/hasStock")
    public R<List<SkuStockDto>> hasStock(@RequestBody List<Long> skuIds) {
        R<List<SkuStockDto>> r = new R<>();
        List<SkuStockDto> skuStockDtos = wareSkuService.getStockByIds(skuIds);
        try {
            r.setData(skuStockDtos);
            r.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    //ware:waresku:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareSkuService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //ware:waresku:info
    public R info(@PathVariable("id") Long id) {
        WareSkuEntity wareSku = wareSkuService.getById(id);
        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //ware:waresku:save
    public R save(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.save(wareSku);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //ware:waresku:update
    public R update(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.updateById(wareSku);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("ware:waresku:delete")
    public R delete(@RequestBody Long[] ids) {
        wareSkuService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
