package com.study.mall.web;

import com.study.mall.common.lang.R;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.dto.OrderEntityDto;
import com.study.mall.feign.IOrderFeignService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/6/26 12:21
 */
@Controller
public class MemberWebController {

    @Resource
    private IOrderFeignService orderFeignService;

    @GetMapping("/memberOrder.html")
    public String memberOrderPage(@RequestParam(required = false, defaultValue = "1") String pageNum, Model model) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("page", pageNum);
        R<PageUtils<OrderEntityDto>> res = orderFeignService.listWithItem(param);
        model.addAttribute("orders", res);
        return "orderList";
    }
}
