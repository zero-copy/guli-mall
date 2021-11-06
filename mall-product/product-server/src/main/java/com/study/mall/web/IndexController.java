package com.study.mall.web;

import com.study.mall.entity.CategoryEntity;
import com.study.mall.service.ICategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 11 07 上午 01:40
 */
@Controller
public class IndexController {

    @Resource
    private ICategoryService categoryService;

    @GetMapping({"/", "/index", "/index.html"})
    public String indexPage(Model model) {
        List<CategoryEntity> rootEntity = categoryService.getRoot();
        model.addAttribute("categorys", rootEntity);
        return "index";
    }
}
