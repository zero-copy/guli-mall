package com.study.mall.controller;

import com.study.mall.common.lang.R;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.OrderEntity;
import com.study.mall.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 订单
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@RestController
@RequestMapping("order/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @GetMapping("/status/{orderSn}")
    public R<OrderEntity> getOrderStatus(@PathVariable String orderSn) {
        OrderEntity order = orderService.getByOrderSn(orderSn);
        return R.ok(order);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:order:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);
        return R.put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:order:info
    public R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.getById(id);
        return R.put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:order:save
    public R save(@RequestBody OrderEntity order) {
        orderService.save(order);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:order:update
    public R update(@RequestBody OrderEntity order) {
        orderService.updateById(order);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:order:delete")
    public R delete(@RequestBody Long[] ids) {
        orderService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @PostMapping("/listWithItem")
    public R<PageUtils<OrderEntity>> listWithItem(@RequestBody Map<String, Object> params) {
        PageUtils<OrderEntity> page = orderService.queryPageWithItem(params);
        return R.ok(page);
    }

}
