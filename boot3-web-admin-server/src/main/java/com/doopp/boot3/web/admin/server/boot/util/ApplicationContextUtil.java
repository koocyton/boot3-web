package com.doopp.boot3.web.admin.server.boot.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static RequestMappingHandlerMapping requestMapping;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
        ApplicationContextUtil.requestMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return applicationContext.getBean(beanName, beanClass);
    }

    @SuppressWarnings("null")
    public static HandlerMethod getRequestMethod(HttpServletRequest request) {
        try {
            HandlerExecutionChain chain = requestMapping.getHandler(request);
            if (ObjectUtils.isEmpty(chain)) {
                return null;
            }
            return (HandlerMethod) chain.getHandler();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
