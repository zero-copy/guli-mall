package com.study.mall.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.mall.common.constant.ProductConstant;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;
import com.study.mall.entity.AttrEntity;
import com.study.mall.entity.ProductAttrValueEntity;
import com.study.mall.service.IAttrService;
import com.study.mall.service.IProductAttrValueService;
import com.study.mall.vo.AttrReqVo;
import com.study.mall.vo.AttrRespVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品属性
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Resource
    private IAttrService attrService;

    @Resource
    private IProductAttrValueService attrValueService;

    @PostMapping("/update/{spuId}")
    public R update(@PathVariable Long spuId, @RequestBody List<ProductAttrValueEntity> attrValueEntities) {
        attrValueService.updateBySpuId(spuId, attrValueEntities);
        return R.ok();
    }

    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrList(@PathVariable Long spuId) {
        List<ProductAttrValueEntity> attrValueEntities = attrValueService
                .list(new QueryWrapper<ProductAttrValueEntity>().eq(ProductAttrValueEntity.SPU_ID, spuId));
        return R.ok(attrValueEntities);
    }

    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@PathVariable String attrType, @PathVariable Long catelogId, @RequestParam Map<String, Object> params) {
        Integer type = ProductConstant.AttrEnum.ATTR_TYPE_BASE.getType().equalsIgnoreCase(attrType) ?
                ProductConstant.AttrEnum.ATTR_TYPE_BASE.getValue() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getValue();
        PageUtils page = attrService.queryAttrPage(type, catelogId, params);
        return R.ok(page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    //product:attr:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    //product:attr:info
    public R info(@PathVariable("attrId") Long attrId) {
        AttrRespVo respVo = attrService.getDetailById(attrId);
        return R.ok().put("attr", respVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //product:attr:save
    public R save(@RequestBody AttrReqVo attr) {
        AttrEntity attrEntity = BeanUtil.copyProperties(attr, AttrEntity.class);
        Long groupId = attr.getAttrGroupId();
        attrService.save(groupId, attrEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //product:attr:update
    public R update(@RequestBody AttrReqVo attr) {
        attrService.updateDetail(attr);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));
        return R.ok();
    }

}
