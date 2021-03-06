package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.HomeSubjectEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.IHomeSubjectService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.lang.R;


/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:25
 */
@RestController
@RequestMapping("coupon/homesubject")
public class HomeSubjectController {

    @Resource
    private IHomeSubjectService homeSubjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //coupon:homesubject:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = homeSubjectService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //coupon:homesubject:info
    public R info(@PathVariable("id") Long id) {
            HomeSubjectEntity homeSubject = homeSubjectService.getById(id);
        return R.ok().put("homeSubject", homeSubject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //coupon:homesubject:save
    public R save(@RequestBody HomeSubjectEntity homeSubject) {
            homeSubjectService.save(homeSubject);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //coupon:homesubject:update
    public R update(@RequestBody HomeSubjectEntity homeSubject) {
            homeSubjectService.updateById(homeSubject);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:homesubject:delete")
    public R delete(@RequestBody Long[] ids) {
            homeSubjectService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
