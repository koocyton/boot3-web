package com.doopp.boot3.web.admin.server.boot.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.doopp.boot3.web.admin.server.boot.util.ApplicationContextUtil;
import com.doopp.boot3.web.admin.server.component.AuthJwtComponent;
import com.doopp.boot3.web.admin.server.pojo.dto.auth.AuthInfo;
import com.doopp.boot3.web.admin.server.service.LoginService;
import com.doopp.boot3.web.core.annotation.NoLogin;
import com.doopp.boot3.web.core.exception.AssertException;
import com.doopp.boot3.web.core.exception.AssertMessage;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Component
public class ApiRequestInterceptor implements HandlerInterceptor {

    @Resource
    protected LoginService loginService;

    @Resource
    protected AuthJwtComponent authJwtComponent;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // get method
        HandlerMethod handlerMethod = ApplicationContextUtil.getRequestMethod(request);
        // 没有找到对应的 handler 或 NoAuth
        // log.info(">>>>> {} {}", request.getRequestURI(), handlerMethod.hasMethodAnnotation(NoLogin.class));
        if (handlerMethod == null || handlerMethod.hasMethodAnnotation(NoLogin.class)) {
            return true;
        }
        // 需要 User Token
        String userToken = request.getHeader("Admin-Token");
        if (ObjectUtils.isEmpty(userToken)) {
            userToken = request.getParameter("Admin-Token");
        }
        if (ObjectUtils.isEmpty(userToken)) {
            throw new AssertException(AssertMessage.TOKEN_NOT_EMPTY);
        }
        // AdminUser sessionUser = loginService.getUserByToken(userToken);
        // if (ObjectUtils.isEmpty(sessionUser)) {
        //     throw new AssertException(AssertMessage.USER_NOT_EXIST);
        // }
        // request.getSession().setAttribute("SessionUser", sessionUser);
        AuthInfo authInfo = authJwtComponent.verifyTokenAndGetAuthInfo(userToken);
        request.getSession().setAttribute("AuthInfo", authInfo);
        return true;
    }
}