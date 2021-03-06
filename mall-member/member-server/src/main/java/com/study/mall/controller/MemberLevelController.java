package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.MemberLevelEntity;
import com.study.mall.service.IMemberLevelService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * 会员等级
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@RestController
@RequestMapping("member/memberlevel")
public class MemberLevelController {

    @Resource
    private IMemberLevelService memberLevelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:memberlevel:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberLevelService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:memberlevel:info
    public R info(@PathVariable("id") Long id) {
            MemberLevelEntity memberLevel = memberLevelService.getById(id);
        return R.ok().put("memberLevel", memberLevel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:memberlevel:save
    public R save(@RequestBody MemberLevelEntity memberLevel) {
            memberLevelService.save(memberLevel);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:memberlevel:update
    public R update(@RequestBody MemberLevelEntity memberLevel) {
            memberLevelService.updateById(memberLevel);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:memberlevel:delete")
    public R delete(@RequestBody Long[] ids) {
            memberLevelService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
