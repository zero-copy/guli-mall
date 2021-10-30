package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.MemberCollectSpuEntity;
import com.study.mall.service.IMemberCollectSpuService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 会员收藏的商品
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@RestController
@RequestMapping("member/membercollectspu")
public class MemberCollectSpuController {

    @Resource
    private IMemberCollectSpuService memberCollectSpuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:membercollectspu:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberCollectSpuService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:membercollectspu:info
    public R info(@PathVariable("id") Long id) {
            MemberCollectSpuEntity memberCollectSpu = memberCollectSpuService.getById(id);
        return R.ok().put("memberCollectSpu", memberCollectSpu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:membercollectspu:save
    public R save(@RequestBody MemberCollectSpuEntity memberCollectSpu) {
            memberCollectSpuService.save(memberCollectSpu);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:membercollectspu:update
    public R update(@RequestBody MemberCollectSpuEntity memberCollectSpu) {
            memberCollectSpuService.updateById(memberCollectSpu);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:membercollectspu:delete")
    public R delete(@RequestBody Long[] ids) {
            memberCollectSpuService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
