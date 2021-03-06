package com.study.mall.web;

import com.study.mall.form.SearchForm;
import com.study.mall.service.IMallSearchService;
import com.study.mall.vo.SearchVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Harlan
 * @date 2021 11 21 下午 04:54
 */
@Controller
public class SearchController {

    @Resource
    private IMallSearchService mallSearchService;

    @GetMapping({"/", "/list.html"})
    public String listPage(SearchForm searchForm, Model model, HttpServletRequest request) {
        if (Objects.isNull(searchForm)) {
            searchForm = new SearchForm();
        }
        String query = request.getQueryString();
        searchForm.set_queryString(query);
        SearchVo searchVo = mallSearchService.search(searchForm);
        model.addAttribute("result", searchVo);
        return "list";
    }
}
