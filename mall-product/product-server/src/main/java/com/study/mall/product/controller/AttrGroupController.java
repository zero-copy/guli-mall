package com.study.mall.product.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.product.entity.AttrGroupEntity;
import com.study.mall.product.service.IAttrGroupService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 属性分组
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    @Resource
    private IAttrGroupService attrGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:attrgroup:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrGroupService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //product:attrgroup:info
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
            AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:attrgroup:save
    public R save(@RequestBody AttrGroupEntity attrGroup) {
            attrGroupService.save(attrGroup);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:attrgroup:update
    public R update(@RequestBody AttrGroupEntity attrGroup) {
            attrGroupService.updateById(attrGroup);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
            attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return R.ok();
    }

}
