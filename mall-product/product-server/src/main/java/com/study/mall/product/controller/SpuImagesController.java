package com.study.mall.product.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.product.entity.SpuImagesEntity;
import com.study.mall.product.service.ISpuImagesService;
import com.study.common.utils.PageUtils;
import com.study.common.utils.R;


/**
 * spu图片
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@RestController
@RequestMapping("product/spuimages")
public class SpuImagesController {

    @Resource
    private ISpuImagesService spuImagesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:spuimages:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = spuImagesService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //product:spuimages:info
    public R info(@PathVariable("id") Long id) {
            SpuImagesEntity spuImages = spuImagesService.getById(id);
        return R.ok().put("spuImages", spuImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:spuimages:save
    public R save(@RequestBody SpuImagesEntity spuImages) {
            spuImagesService.save(spuImages);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:spuimages:update
    public R update(@RequestBody SpuImagesEntity spuImages) {
            spuImagesService.updateById(spuImages);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:spuimages:delete")
    public R delete(@RequestBody Long[] ids) {
            spuImagesService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
