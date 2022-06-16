package com.study.mall.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.common.dto.MemberEntityDto;
import com.study.mall.common.lang.R;
import com.study.mall.dto.MemberLoginDto;
import com.study.mall.dto.SocialUserDto;
import com.study.mall.entity.SocialUser;
import com.study.mall.feign.IMemberFeignService;
import com.study.mall.form.UserLoginForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Harlan
 * @date 2022 06 09 下午 01:24
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Value("${oauth2.gitee.client-id}")
    private String giteeId;

    @Value("${oauth2.gitee.client-secret}")
    private String giteeSecret;

    @Resource
    private IMemberFeignService memberFeignService;

    @GetMapping("/login.html")
    public String loginPage(HttpSession session) {
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (Objects.isNull(attribute)) {
            return "login";
        }
        return "redirect:http://gulimall.com";
    }

    @PostMapping("/login")
    public String login(UserLoginForm userLoginForm, RedirectAttributes attributes, HttpSession session) {
        MemberLoginDto loginDto = BeanUtil.copyProperties(userLoginForm, MemberLoginDto.class);
        R<MemberEntityDto>  result = memberFeignService.login(loginDto);
        if (result.getCode() == 0) {
            //success
            MemberEntityDto memberEntityDto = result.getData();
            session.setAttribute(AuthServerConstant.LOGIN_USER, memberEntityDto);
            return "redirect:http://gulimall.com";
        }
        attributes.addFlashAttribute("errors", result.getMsg());
        return "redirect:http://auth.gulimall.com/login.html";
    }

    @GetMapping("/oauth2.0/gitee")
    public String oauthGitee(@RequestParam String code, HttpSession session) {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("code", code);
        requestParams.put("grant_type", "authorization_code");
        requestParams.put("client_id", giteeId);
        requestParams.put("client_secret", giteeSecret);
        requestParams.put("redirect_uri", "http://auth.gulimall.com/login/oauth2.0/gitee");
        HttpRequest request = HttpUtil.createPost("https://gitee.com/oauth/token");
        request.form(requestParams);
        HttpResponse response = request.execute();
        if (response.isOk()) {
            SocialUser socialUser = JSON.parseObject(response.body(), SocialUser.class);
            HttpResponse userInfoResp = HttpRequest.get("https://gitee.com/api/v5/user")
                    .form("access_token", socialUser.getAccessToken())
                    .execute();
            if (userInfoResp.isOk()) {
                JSONObject userInfoJson = JSON.parseObject(userInfoResp.body());
                socialUser.setId(userInfoJson.getString("id"));
                socialUser.setAvatarUrl(userInfoJson.getString("avatar_url"));
                socialUser.setName(userInfoJson.getString("name"));
            }
            SocialUserDto socialUserDto = BeanUtil.copyProperties(socialUser, SocialUserDto.class);
            R<MemberEntityDto> memberEntityDto = memberFeignService.oauthLogin(socialUserDto);
            session.setAttribute(AuthServerConstant.LOGIN_USER, memberEntityDto);
            return "redirect:http://gulimall.com";
        }
        return "redirect:http:auth.gulimall.com/login.html";
    }
}
