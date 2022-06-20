package com.study.mall.interceptor;

import com.study.mall.common.constant.AuthServerConstant;
import com.study.mall.common.dto.TempUserInfo;
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

    private static final ThreadLocal<TempUserInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (Objects.isNull(user)) {
            response.sendRedirect("http://auth.gulimall.com/login/login.html");
            return false;
        } else {
            THREAD_LOCAL.set((TempUserInfo) user);
            return true;
        }
    }
}
