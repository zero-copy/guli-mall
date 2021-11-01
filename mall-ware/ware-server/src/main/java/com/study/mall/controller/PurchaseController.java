package com.study.mall.controller;

import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;
import com.study.mall.entity.PurchaseDetailEntity;
import com.study.mall.entity.PurchaseEntity;
import com.study.mall.form.MergeForm;
import com.study.mall.form.PurchaseDoneForm;
import com.study.mall.service.IPurchaseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 采购信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {

    @Resource
    private IPurchaseService purchaseService;

    @PostMapping("/done")
    public R finished(@RequestBody @Validated PurchaseDoneForm doneForm) {
        Long purchaseId = doneForm.getId();
        List<PurchaseDetailEntity> detailEntities = doneForm.getItems().stream().map(item -> {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            detailEntity.setPurchaseId(purchaseId);
            detailEntity.setId(item.getItemId());
            detailEntity.setStatus(item.getStatus());
            return detailEntity;
        }).collect(Collectors.toList());
        purchaseService.done(purchaseId, detailEntities);
        return R.ok();
    }

    @PostMapping("/received")
    public R received(@RequestBody List<Long> purchaseIds) {
        purchaseService.received(purchaseIds);
        return R.ok();
    }

    @PostMapping("/merge")
    public R merge(@RequestBody MergeForm mergeForm) {
        purchaseService.merge(mergeForm.getPurchaseId(), mergeForm.getItems());
        return R.ok();
    }

    @GetMapping("/unreceive/list")
    public R unreceiveList(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPageUnreceive(params);
        return R.ok(page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    //ware:purchase:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //ware:purchase:info
    public R info(@PathVariable("id") Long id) {
        PurchaseEntity purchase = purchaseService.getById(id);
        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //ware:purchase:save
    public R save(@RequestBody PurchaseEntity purchase) {
        purchase.setUpdateTime(LocalDateTime.now());
        purchase.setCreateTime(LocalDateTime.now());
        purchaseService.save(purchase);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //ware:purchase:update
    public R update(@RequestBody PurchaseEntity purchase) {
        purchase.setUpdateTime(LocalDateTime.now());
        purchaseService.updateById(purchase);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids) {
        purchaseService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
