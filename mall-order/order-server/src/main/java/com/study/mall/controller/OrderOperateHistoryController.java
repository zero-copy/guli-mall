package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.OrderOperateHistoryEntity;
import com.study.mall.service.IOrderOperateHistoryService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 订单操作历史记录
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@RestController
@RequestMapping("order/orderoperatehistory")
public class OrderOperateHistoryController {

    @Resource
    private IOrderOperateHistoryService orderOperateHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:orderoperatehistory:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderOperateHistoryService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //order:orderoperatehistory:info
    public R info(@PathVariable("id") Long id) {
            OrderOperateHistoryEntity orderOperateHistory = orderOperateHistoryService.getById(id);
        return R.ok().put("orderOperateHistory", orderOperateHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:orderoperatehistory:save
    public R save(@RequestBody OrderOperateHistoryEntity orderOperateHistory) {
            orderOperateHistoryService.save(orderOperateHistory);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:orderoperatehistory:update
    public R update(@RequestBody OrderOperateHistoryEntity orderOperateHistory) {
            orderOperateHistoryService.updateById(orderOperateHistory);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderoperatehistory:delete")
    public R delete(@RequestBody Long[] ids) {
            orderOperateHistoryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
