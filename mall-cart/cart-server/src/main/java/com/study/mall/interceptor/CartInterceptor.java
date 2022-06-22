package com.study.mall.interceptor;

import cn.hutool.core.lang.UUID;
import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.common.dto.MemberEntityDto;
import com.study.mall.common.dto.TempUserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author Harlan
 * @date 2022 06 17 下午 01:09
 */
public class CartInterceptor implements HandlerInterceptor {

    public static final ThreadLocal<TempUserInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TempUserInfo tempUserInfo = new TempUserInfo();
        HttpSession session = request.getSession();
        MemberEntityDto memberDto = (MemberEntityDto) session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (Objects.nonNull(memberDto)) {
            tempUserInfo.setUserId(memberDto.getId());
            tempUserInfo.setIntegration(memberDto.getIntegration());
        }
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies) && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthServerConstant.TEMP_USER_KEY)) {
                    tempUserInfo.setUserKey(cookie.getValue());
                    tempUserInfo.setExpired(false);
                    break;
                }
            }
        }
        //添加临时用户
        if (Objects.isNull(tempUserInfo.getUserKey())) {
            tempUserInfo.setExpired(true);
            String uuid = UUID.randomUUID().toString();
            tempUserInfo.setUserKey(uuid);
        }
        THREAD_LOCAL.set(tempUserInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (THREAD_LOCAL.get().isExpired()) {
            Cookie cookie = new Cookie(AuthServerConstant.TEMP_USER_KEY, THREAD_LOCAL.get().getUserKey());
            cookie.setMaxAge(AuthServerConstant.TEMP_USER_COOKIE_TIME_OUT);
            cookie.setDomain("gulimall.com");
            response.addCookie(cookie);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        THREAD_LOCAL.remove();
    }
}
