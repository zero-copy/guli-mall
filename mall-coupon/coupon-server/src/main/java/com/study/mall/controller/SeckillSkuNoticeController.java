package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.SeckillSkuNoticeEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.ISeckillSkuNoticeService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 秒杀商品通知订阅
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@RestController
@RequestMapping("coupon/seckillskunotice")
public class SeckillSkuNoticeController {

    @Resource
    private ISeckillSkuNoticeService seckillSkuNoticeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:seckillskunotice:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = seckillSkuNoticeService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:seckillskunotice:info
    public R info(@PathVariable("id") Long id) {
            SeckillSkuNoticeEntity seckillSkuNotice = seckillSkuNoticeService.getById(id);
        return R.ok().put("seckillSkuNotice", seckillSkuNotice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:seckillskunotice:save
    public R save(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice) {
            seckillSkuNoticeService.save(seckillSkuNotice);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:seckillskunotice:update
    public R update(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice) {
            seckillSkuNoticeService.updateById(seckillSkuNotice);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillskunotice:delete")
    public R delete(@RequestBody Long[] ids) {
            seckillSkuNoticeService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
