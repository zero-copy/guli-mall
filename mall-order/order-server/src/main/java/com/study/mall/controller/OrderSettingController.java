package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.OrderSettingEntity;
import com.study.mall.service.IOrderSettingService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 订单配置信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:48
 */
@RestController
@RequestMapping("order/ordersetting")
public class OrderSettingController {

    @Resource
    private IOrderSettingService orderSettingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:ordersetting:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderSettingService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:ordersetting:info
    public R info(@PathVariable("id") Long id) {
            OrderSettingEntity orderSetting = orderSettingService.getById(id);
        return R.ok().put("orderSetting", orderSetting);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:ordersetting:save
    public R save(@RequestBody OrderSettingEntity orderSetting) {
            orderSettingService.save(orderSetting);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:ordersetting:update
    public R update(@RequestBody OrderSettingEntity orderSetting) {
            orderSettingService.updateById(orderSetting);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:ordersetting:delete")
    public R delete(@RequestBody Long[] ids) {
            orderSettingService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
