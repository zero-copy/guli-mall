package com.study.mall.member.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.member.entity.GrowthChangeHistoryEntity;
import com.study.mall.member.service.IGrowthChangeHistoryService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * 成长值变化历史记录
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:58
 */
@RestController
@RequestMapping("member/growthchangehistory")
public class GrowthChangeHistoryController {

    @Resource
    private IGrowthChangeHistoryService growthChangeHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:growthchangehistory:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = growthChangeHistoryService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:growthchangehistory:info
    public R info(@PathVariable("id") Long id) {
            GrowthChangeHistoryEntity growthChangeHistory = growthChangeHistoryService.getById(id);
        return R.ok().put("growthChangeHistory", growthChangeHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:growthchangehistory:save
    public R save(@RequestBody GrowthChangeHistoryEntity growthChangeHistory) {
            growthChangeHistoryService.save(growthChangeHistory);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:growthchangehistory:update
    public R update(@RequestBody GrowthChangeHistoryEntity growthChangeHistory) {
            growthChangeHistoryService.updateById(growthChangeHistory);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:growthchangehistory:delete")
    public R delete(@RequestBody Long[] ids) {
            growthChangeHistoryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
