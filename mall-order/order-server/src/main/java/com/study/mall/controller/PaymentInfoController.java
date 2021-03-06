package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.PaymentInfoEntity;
import com.study.mall.service.IPaymentInfoService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * 支付信息表
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@RestController
@RequestMapping("order/paymentinfo")
public class PaymentInfoController {

    @Resource
    private IPaymentInfoService paymentInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:paymentinfo:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = paymentInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:paymentinfo:info
    public R info(@PathVariable("id") Long id) {
            PaymentInfoEntity paymentInfo = paymentInfoService.getById(id);
        return R.ok().put("paymentInfo", paymentInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:paymentinfo:save
    public R save(@RequestBody PaymentInfoEntity paymentInfo) {
            paymentInfoService.save(paymentInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:paymentinfo:update
    public R update(@RequestBody PaymentInfoEntity paymentInfo) {
            paymentInfoService.updateById(paymentInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:paymentinfo:delete")
    public R delete(@RequestBody Long[] ids) {
            paymentInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
