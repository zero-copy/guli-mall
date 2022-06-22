package com.study.mall.interceptor;

import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.common.dto.MemberEntityDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author Harlan
 * @date 2022 06 20 下午 08:40
 */
public class LoginInterceptor implements HandlerInterceptor {

    public static final ThreadLocal<MemberEntityDto> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (Objects.isNull(user)) {
            response.sendRedirect("http://auth.gulimall.com/login/login.html");
            return false;
        } else {
            THREAD_LOCAL.set((MemberEntityDto) user);
            return true;
        }
    }
}
