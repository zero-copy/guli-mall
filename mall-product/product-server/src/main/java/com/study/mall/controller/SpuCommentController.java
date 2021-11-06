package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.SpuCommentEntity;
import com.study.mall.service.ISpuCommentService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * 商品评价
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@RestController
@RequestMapping("product/spucomment")
public class SpuCommentController {

    @Resource
    private ISpuCommentService spuCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:spucomment:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = spuCommentService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //product:spucomment:info
    public R info(@PathVariable("id") Long id) {
            SpuCommentEntity spuComment = spuCommentService.getById(id);
        return R.ok().put("spuComment", spuComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:spucomment:save
    public R save(@RequestBody SpuCommentEntity spuComment) {
            spuCommentService.save(spuComment);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:spucomment:update
    public R update(@RequestBody SpuCommentEntity spuComment) {
            spuCommentService.updateById(spuComment);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:spucomment:delete")
    public R delete(@RequestBody Long[] ids) {
            spuCommentService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
