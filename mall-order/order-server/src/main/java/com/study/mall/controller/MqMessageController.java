package com.study.mall.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.entity.MqMessageEntity;
import com.study.mall.service.IMqMessageService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:41:49
 */
@RestController
@RequestMapping("order/mqmessage")
public class MqMessageController {

    @Resource
    private IMqMessageService mqMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //order:mqmessage:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = mqMessageService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{messageId}")
    //order:mqmessage:info
    public R info(@PathVariable("messageId") String messageId) {
            MqMessageEntity mqMessage = mqMessageService.getById(messageId);
        return R.ok().put("mqMessage", mqMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //order:mqmessage:save
    public R save(@RequestBody MqMessageEntity mqMessage) {
            mqMessageService.save(mqMessage);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //order:mqmessage:update
    public R update(@RequestBody MqMessageEntity mqMessage) {
            mqMessageService.updateById(mqMessage);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:mqmessage:delete")
    public R delete(@RequestBody String[] messageIds) {
            mqMessageService.removeByIds(Arrays.asList(messageIds));
        return R.ok();
    }

}
