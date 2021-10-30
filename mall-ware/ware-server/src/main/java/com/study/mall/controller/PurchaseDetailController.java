package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.PurchaseDetailEntity;
import com.study.mall.service.IPurchaseDetailService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@RestController
@RequestMapping("ware/purchasedetail")
public class PurchaseDetailController {

    @Resource
    private IPurchaseDetailService purchaseDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //ware:purchasedetail:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseDetailService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //ware:purchasedetail:info
    public R info(@PathVariable("id") Long id) {
            PurchaseDetailEntity purchaseDetail = purchaseDetailService.getById(id);
        return R.ok().put("purchaseDetail", purchaseDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //ware:purchasedetail:save
    public R save(@RequestBody PurchaseDetailEntity purchaseDetail) {
            purchaseDetailService.save(purchaseDetail);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //ware:purchasedetail:update
    public R update(@RequestBody PurchaseDetailEntity purchaseDetail) {
            purchaseDetailService.updateById(purchaseDetail);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:purchasedetail:delete")
    public R delete(@RequestBody Long[] ids) {
            purchaseDetailService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
