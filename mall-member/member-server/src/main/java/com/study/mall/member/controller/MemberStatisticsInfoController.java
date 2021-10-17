package com.study.mall.member.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.member.entity.MemberStatisticsInfoEntity;
import com.study.mall.member.service.IMemberStatisticsInfoService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 会员统计信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@RestController
@RequestMapping("member/memberstatisticsinfo")
public class MemberStatisticsInfoController {

    @Resource
    private IMemberStatisticsInfoService memberStatisticsInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:memberstatisticsinfo:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberStatisticsInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:memberstatisticsinfo:info
    public R info(@PathVariable("id") Long id) {
            MemberStatisticsInfoEntity memberStatisticsInfo = memberStatisticsInfoService.getById(id);
        return R.ok().put("memberStatisticsInfo", memberStatisticsInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:memberstatisticsinfo:save
    public R save(@RequestBody MemberStatisticsInfoEntity memberStatisticsInfo) {
            memberStatisticsInfoService.save(memberStatisticsInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:memberstatisticsinfo:update
    public R update(@RequestBody MemberStatisticsInfoEntity memberStatisticsInfo) {
            memberStatisticsInfoService.updateById(memberStatisticsInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:memberstatisticsinfo:delete")
    public R delete(@RequestBody Long[] ids) {
            memberStatisticsInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
