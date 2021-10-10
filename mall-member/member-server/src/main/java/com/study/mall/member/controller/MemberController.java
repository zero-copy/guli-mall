package com.study.mall.member.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.member.entity.MemberEntity;
import com.study.mall.member.service.IMemberService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * 会员
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:58
 */
@RestController
@RequestMapping("member/member")
public class MemberController {

    @Resource
    private IMemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:member:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:member:info
    public R info(@PathVariable("id") Long id) {
            MemberEntity member = memberService.getById(id);
        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:member:save
    public R save(@RequestBody MemberEntity member) {
            memberService.save(member);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:member:update
    public R update(@RequestBody MemberEntity member) {
            memberService.updateById(member);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids) {
            memberService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
