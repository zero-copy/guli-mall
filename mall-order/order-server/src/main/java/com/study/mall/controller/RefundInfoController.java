package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.RefundInfoEntity;
import com.study.mall.service.IRefundInfoService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * 退款信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@RestController
@RequestMapping("order/refundinfo")
public class RefundInfoController {

    @Resource
    private IRefundInfoService refundInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:refundinfo:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = refundInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:refundinfo:info
    public R info(@PathVariable("id") Long id) {
            RefundInfoEntity refundInfo = refundInfoService.getById(id);
        return R.ok().put("refundInfo", refundInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:refundinfo:save
    public R save(@RequestBody RefundInfoEntity refundInfo) {
            refundInfoService.save(refundInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:refundinfo:update
    public R update(@RequestBody RefundInfoEntity refundInfo) {
            refundInfoService.updateById(refundInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:refundinfo:delete")
    public R delete(@RequestBody Long[] ids) {
            refundInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
