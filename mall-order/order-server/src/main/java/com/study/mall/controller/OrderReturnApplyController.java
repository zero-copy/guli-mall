package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.OrderReturnApplyEntity;
import com.study.mall.service.IOrderReturnApplyService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 订单退货申请
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@RestController
@RequestMapping("order/orderreturnapply")
public class OrderReturnApplyController {

    @Resource
    private IOrderReturnApplyService orderReturnApplyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:orderreturnapply:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderReturnApplyService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:orderreturnapply:info
    public R info(@PathVariable("id") Long id) {
            OrderReturnApplyEntity orderReturnApply = orderReturnApplyService.getById(id);
        return R.ok().put("orderReturnApply", orderReturnApply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:orderreturnapply:save
    public R save(@RequestBody OrderReturnApplyEntity orderReturnApply) {
            orderReturnApplyService.save(orderReturnApply);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:orderreturnapply:update
    public R update(@RequestBody OrderReturnApplyEntity orderReturnApply) {
            orderReturnApplyService.updateById(orderReturnApply);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderreturnapply:delete")
    public R delete(@RequestBody Long[] ids) {
            orderReturnApplyService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
