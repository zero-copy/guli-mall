package com.study.mall.controller;

import com.study.mall.common.lang.R;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.MemberReceiveAddressEntity;
import com.study.mall.service.IMemberReceiveAddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 会员收货地址
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@RestController
@RequestMapping("member/memberreceiveaddress")
public class MemberReceiveAddressController {

    @Resource
    private IMemberReceiveAddressService memberReceiveAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:memberreceiveaddress:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberReceiveAddressService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:memberreceiveaddress:info
    public R info(@PathVariable("id") Long id) {
        MemberReceiveAddressEntity memberReceiveAddress = memberReceiveAddressService.getById(id);
        return R.ok().put("memberReceiveAddress", memberReceiveAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:memberreceiveaddress:save
    public R save(@RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
        memberReceiveAddressService.save(memberReceiveAddress);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:memberreceiveaddress:update
    public R update(@RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
        memberReceiveAddressService.updateById(memberReceiveAddress);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:memberreceiveaddress:delete")
    public R delete(@RequestBody Long[] ids) {
        memberReceiveAddressService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/{memberId}/address")
    public R<List<MemberReceiveAddressEntity>> getAddress(@PathVariable Long memberId) {
        List<MemberReceiveAddressEntity> address = memberReceiveAddressService.getAddress(memberId);
        return R.ok(address);
    }

}
