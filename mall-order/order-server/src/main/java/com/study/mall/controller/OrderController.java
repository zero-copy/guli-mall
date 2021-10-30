package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.OrderEntity;
import com.study.mall.service.IOrderService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


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

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:order:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:order:info
    public R info(@PathVariable("id") Long id) {
            OrderEntity order = orderService.getById(id);
        return R.ok().put("order", order);
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

}
