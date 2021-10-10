package com.study.mall.order.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.order.entity.OrderReturnReasonEntity;
import com.study.mall.order.service.IOrderReturnReasonService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * 退货原因
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@RestController
@RequestMapping("order/orderreturnreason")
public class OrderReturnReasonController {

    @Resource
    private IOrderReturnReasonService orderReturnReasonService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:orderreturnreason:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderReturnReasonService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:orderreturnreason:info
    public R info(@PathVariable("id") Long id) {
            OrderReturnReasonEntity orderReturnReason = orderReturnReasonService.getById(id);
        return R.ok().put("orderReturnReason", orderReturnReason);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:orderreturnreason:save
    public R save(@RequestBody OrderReturnReasonEntity orderReturnReason) {
            orderReturnReasonService.save(orderReturnReason);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:orderreturnreason:update
    public R update(@RequestBody OrderReturnReasonEntity orderReturnReason) {
            orderReturnReasonService.updateById(orderReturnReason);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderreturnreason:delete")
    public R delete(@RequestBody Long[] ids) {
            orderReturnReasonService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
