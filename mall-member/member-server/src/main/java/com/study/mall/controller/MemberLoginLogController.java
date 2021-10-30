package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.MemberLoginLogEntity;
import com.study.mall.service.IMemberLoginLogService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 会员登录记录
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@RestController
@RequestMapping("member/memberloginlog")
public class MemberLoginLogController {

    @Resource
    private IMemberLoginLogService memberLoginLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:memberloginlog:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberLoginLogService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:memberloginlog:info
    public R info(@PathVariable("id") Long id) {
            MemberLoginLogEntity memberLoginLog = memberLoginLogService.getById(id);
        return R.ok().put("memberLoginLog", memberLoginLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:memberloginlog:save
    public R save(@RequestBody MemberLoginLogEntity memberLoginLog) {
            memberLoginLogService.save(memberLoginLog);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:memberloginlog:update
    public R update(@RequestBody MemberLoginLogEntity memberLoginLog) {
            memberLoginLogService.updateById(memberLoginLog);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:memberloginlog:delete")
    public R delete(@RequestBody Long[] ids) {
            memberLoginLogService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
