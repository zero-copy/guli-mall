package com.study.mall.product.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;
import com.study.mall.product.entity.AttrAttrgroupRelationEntity;
import com.study.mall.product.entity.AttrEntity;
import com.study.mall.product.entity.AttrGroupEntity;
import com.study.mall.product.service.IAttrAttrgroupRelationService;
import com.study.mall.product.service.IAttrGroupService;
import com.study.mall.product.service.IAttrService;
import com.study.mall.product.service.ICategoryService;
import com.study.mall.product.vo.AttrGroupRelationReqVo;
import com.study.mall.product.vo.AttrGroupWithAttrVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    @Resource
    private ICategoryService categoryService;

    @Resource
    private IAttrAttrgroupRelationService relationService;

    @Resource
    private IAttrService attrService;

    @GetMapping("/{catelogId}/withattr")
    public R categoryWithAttr(@PathVariable Long catelogId) {
        List<AttrGroupEntity> groupEntities = attrGroupService.list(
                new QueryWrapper<AttrGroupEntity>().eq(AttrGroupEntity.CATELOG_ID, catelogId)
        );
        List<AttrGroupWithAttrVo> vos = groupEntities.stream().map(group -> {
            AttrGroupWithAttrVo vo = BeanUtil.copyProperties(group, AttrGroupWithAttrVo.class);
            List<AttrEntity> attrEntities = attrService.getGroupAttr(group.getAttrGroupId());
            vo.setAttrs(attrEntities);
            return vo;
        }).collect(Collectors.toList());
        return R.ok(vos);
    }

    @PostMapping("/attr/relation")
    public R saveRelation(@RequestBody List<AttrGroupRelationReqVo> relationReqVos) {
        List<AttrAttrgroupRelationEntity> relationEntities = relationReqVos.stream()
                .map(vo -> BeanUtil.copyProperties(vo, AttrAttrgroupRelationEntity.class))
                .collect(Collectors.toList());
        relationService.saveBatch(relationEntities);
        return R.ok();
    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrGroupId, @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelation(attrGroupId, params);
        return R.ok(page);
    }

    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrGroupId) {
        List<AttrEntity> attrEntities = attrService.getGroupAttr(attrGroupId);
        return R.ok(attrEntities);
    }

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody List<AttrGroupRelationReqVo> relationReqVos) {
        List<AttrAttrgroupRelationEntity> relationEntities = relationReqVos.stream()
                .map(vo -> BeanUtil.copyProperties(vo, AttrAttrgroupRelationEntity.class))
                .collect(Collectors.toList());
        attrGroupService.removeRelation(relationEntities);
        return R.ok();
    }

    /**
     * 列表
     */
    @GetMapping("/list/{catelogId}")
    //product:attrgroup:list
    public R list(@RequestParam Map<String, Object> params,@PathVariable("catelogId") Long catelogId) {
        PageUtils page = attrGroupService.queryPageByCatelogId(params, catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    //product:attrgroup:info
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        List<Long> catelogPath = categoryService.findCatelogPath(attrGroup.getCatelogId());
        attrGroup.setCatelogPath(catelogPath.toArray(new Long[0]));
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
