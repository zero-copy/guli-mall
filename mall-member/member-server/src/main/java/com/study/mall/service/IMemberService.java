package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.MemberEntity;
import com.study.mall.entity.SocialUser;

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

    /**
     * 用户登陆
     * @param account 账号
     * @param password 密码
     * @return 用户信息
     */
    MemberEntity login(String account, String password);

    /**
     * 社交登陆
     * @param socialUser 社交账号信息
     * @return 用户信息
     */
    MemberEntity login(SocialUser socialUser);
}

