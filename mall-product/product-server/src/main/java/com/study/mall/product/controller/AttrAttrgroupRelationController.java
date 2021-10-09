package com.study.mall.product.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.product.entity.AttrAttrgroupRelationEntity;
import com.study.mall.product.service.IAttrAttrgroupRelationService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * 属性&属性分组关联
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/attrattrgrouprelation")
public class AttrAttrgroupRelationController {

    @Resource
    private IAttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:attrattrgrouprelation:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrAttrgroupRelationService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //product:attrattrgrouprelation:info
    public R info(@PathVariable("id") Long id) {
            AttrAttrgroupRelationEntity attrAttrgroupRelation = attrAttrgroupRelationService.getById(id);
        return R.ok().put("attrAttrgroupRelation", attrAttrgroupRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:attrattrgrouprelation:save
    public R save(@RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelation) {
            attrAttrgroupRelationService.save(attrAttrgroupRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:attrattrgrouprelation:update
    public R update(@RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelation) {
            attrAttrgroupRelationService.updateById(attrAttrgroupRelation);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrattrgrouprelation:delete")
    public R delete(@RequestBody Long[] ids) {
            attrAttrgroupRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
