package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.OrderItemEntity;
import com.study.mall.service.IOrderItemService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 订单项信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@RestController
@RequestMapping("order/orderitem")
public class OrderItemController {

    @Resource
    private IOrderItemService orderItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:orderitem:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderItemService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:orderitem:info
    public R info(@PathVariable("id") Long id) {
            OrderItemEntity orderItem = orderItemService.getById(id);
        return R.ok().put("orderItem", orderItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:orderitem:save
    public R save(@RequestBody OrderItemEntity orderItem) {
            orderItemService.save(orderItem);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:orderitem:update
    public R update(@RequestBody OrderItemEntity orderItem) {
            orderItemService.updateById(orderItem);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderitem:delete")
    public R delete(@RequestBody Long[] ids) {
            orderItemService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
