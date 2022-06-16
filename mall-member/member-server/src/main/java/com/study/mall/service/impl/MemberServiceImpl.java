package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.exception.MallException;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.MemberEntity;
import com.study.mall.entity.MemberLevelEntity;
import com.study.mall.entity.SocialUser;
import com.study.mall.mapper.MemberMapper;
import com.study.mall.service.IMemberLevelService;
import com.study.mall.service.IMemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 会员
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:58
 */
@Service("memberService")
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements IMemberService {

    @Resource
    private IMemberLevelService memberLevelService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public void register(MemberEntity memberEntity, String password) {
        MemberLevelEntity levelEntity = memberLevelService.getDefaultLevel();
        memberEntity.setLevelId(levelEntity.getId());
        boolean isUnique = checkPhoneNumUnique(memberEntity.getMobile()) && checkUsernameUnique(memberEntity.getUsername());
        if (!isUnique) {
            throw new MallException("用户信息已存在");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);
        memberEntity.setPassword(encodePassword);
        baseMapper.insert(memberEntity);
    }

    @Override
    public MemberEntity login(String account, String password) {
        List<MemberEntity> memberEntities = baseMapper.selectList(new QueryWrapper<MemberEntity>().eq(MemberEntity.USERNAME, account).or().eq(MemberEntity.MOBILE, account));
        if (memberEntities.isEmpty()) {
            //无用户
            return null;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (MemberEntity memberEntity : memberEntities) {
            String encodePassword = memberEntity.getPassword();
            boolean matches = passwordEncoder.matches(password, encodePassword);
            if (matches) {
                return memberEntity;
            }
        }
        return null;
    }

    @Override
    public MemberEntity login(SocialUser socialUser) {
        MemberEntity memberEntity = baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq(MemberEntity.SOCIAL_UID, socialUser.getId()));
        if (Objects.nonNull(memberEntity)) {
            memberEntity.setAccessToken(socialUser.getAccessToken());
            memberEntity.setExpiresIn(socialUser.getExpiresIn());
            baseMapper.updateById(memberEntity);
        } else {
            memberEntity = new MemberEntity();
            memberEntity.setUsername(socialUser.getName());
            memberEntity.setLevelId(memberLevelService.getDefaultLevel().getId());
            memberEntity.setAccessToken(socialUser.getAccessToken());
            memberEntity.setExpiresIn(socialUser.getExpiresIn());
            memberEntity.setSocialUid(socialUser.getId());
            baseMapper.insert(memberEntity);
        }
        return memberEntity;
    }

    @Override
    public boolean checkPhoneNumUnique(String phoneNum) {
        return Objects.isNull(baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq(MemberEntity.MOBILE, phoneNum)));
    }

    @Override
    public boolean checkUsernameUnique(String username) {
        return Objects.isNull(baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq(MemberEntity.USERNAME, username)));
    }

    @Override
    public boolean checkEmailUnique(String email) {
        return Objects.isNull(baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq(MemberEntity.EMAIL, email)));
    }

}