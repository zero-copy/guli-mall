package com.study.mall.service;

import com.study.mall.form.SearchForm;
import com.study.mall.vo.SearchVo;

/**
 * @author Harlan
 * @date 2021 11 27 下午 05:21
 */
public interface IMallSearchService {

    /**
     * 检索
     * @param searchParam 检索参数
     * @return 结果
     */
    SearchVo search(SearchForm searchParam);
}
