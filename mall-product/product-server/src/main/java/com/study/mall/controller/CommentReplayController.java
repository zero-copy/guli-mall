package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;

import com.study.mall.entity.CommentReplayEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.service.ICommentReplayService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 商品评价回复关系
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:56
 */
@RestController
@RequestMapping("product/commentreplay")
public class CommentReplayController {

    @Resource
    private ICommentReplayService commentReplayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:commentreplay:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = commentReplayService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //product:commentreplay:info
    public R info(@PathVariable("id") Long id) {
            CommentReplayEntity commentReplay = commentReplayService.getById(id);
        return R.ok().put("commentReplay", commentReplay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:commentreplay:save
    public R save(@RequestBody CommentReplayEntity commentReplay) {
            commentReplayService.save(commentReplay);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:commentreplay:update
    public R update(@RequestBody CommentReplayEntity commentReplay) {
            commentReplayService.updateById(commentReplay);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:commentreplay:delete")
    public R delete(@RequestBody Long[] ids) {
            commentReplayService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
