package com.study.mall.product.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.product.entity.ProductAttrValueEntity;
import com.study.mall.product.service.IProductAttrValueService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * spu属性值
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/productattrvalue")
public class ProductAttrValueController {

    @Resource
    private IProductAttrValueService productAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:productattrvalue:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = productAttrValueService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //product:productattrvalue:info
    public R info(@PathVariable("id") Long id) {
            ProductAttrValueEntity productAttrValue = productAttrValueService.getById(id);
        return R.ok().put("productAttrValue", productAttrValue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:productattrvalue:save
    public R save(@RequestBody ProductAttrValueEntity productAttrValue) {
            productAttrValueService.save(productAttrValue);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:productattrvalue:update
    public R update(@RequestBody ProductAttrValueEntity productAttrValue) {
            productAttrValueService.updateById(productAttrValue);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:productattrvalue:delete")
    public R delete(@RequestBody Long[] ids) {
            productAttrValueService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
