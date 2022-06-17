package com.study.mall.service.impl;

import com.study.mall.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harlan
 * @date 2022 06 17 下午 12:17
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class CartServiceImpl implements ICartService {


}
