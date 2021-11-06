package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.IntegrationChangeHistoryEntity;
import com.study.mall.service.IIntegrationChangeHistoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * 积分变化历史记录
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:58
 */
@RestController
@RequestMapping("member/integrationchangehistory")
public class IntegrationChangeHistoryController {

    @Resource
    private IIntegrationChangeHistoryService integrationChangeHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:integrationchangehistory:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = integrationChangeHistoryService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:integrationchangehistory:info
    public R info(@PathVariable("id") Long id) {
            IntegrationChangeHistoryEntity integrationChangeHistory = integrationChangeHistoryService.getById(id);
        return R.ok().put("integrationChangeHistory", integrationChangeHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:integrationchangehistory:save
    public R save(@RequestBody IntegrationChangeHistoryEntity integrationChangeHistory) {
            integrationChangeHistoryService.save(integrationChangeHistory);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:integrationchangehistory:update
    public R update(@RequestBody IntegrationChangeHistoryEntity integrationChangeHistory) {
            integrationChangeHistoryService.updateById(integrationChangeHistory);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:integrationchangehistory:delete")
    public R delete(@RequestBody Long[] ids) {
            integrationChangeHistoryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
