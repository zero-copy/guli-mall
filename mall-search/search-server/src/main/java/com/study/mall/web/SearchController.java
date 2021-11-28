package com.study.mall.web;

import com.study.mall.form.SearchForm;
import com.study.mall.service.IMallSearchService;
import com.study.mall.vo.SearchVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author Harlan
 * @date 2021 11 21 下午 04:54
 */
@Controller
public class SearchController {

    @Resource
    private IMallSearchService mallSearchService;

    @GetMapping({"/", "/list.html"})
    public String listPage(@RequestParam(required = false) SearchForm searchForm, Model model) {
        SearchVo searchVo = mallSearchService.search(searchForm);
        model.addAttribute(searchVo);
        return "list";
    }
}
