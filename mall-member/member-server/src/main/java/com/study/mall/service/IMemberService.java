package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:58
 */
public interface IMemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 注册用户
     * @param password 密码
     * @param memberEntity 用户信息
     */
    void register(MemberEntity memberEntity, String password);

    boolean checkPhoneNumUnique(String phoneNum);

    boolean checkUsernameUnique(String username);

    boolean checkEmailUnique(String email);
}

