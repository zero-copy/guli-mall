package com.study.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.common.utils.PageUtils;
import com.study.mall.member.entity.MemberEntity;
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
}

