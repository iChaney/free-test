package com.qiang.config;

import com.alibaba.fastjson.JSONObject;
import com.qiang.annotations.ApiRequestBody;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 自定义参数解析器, 用@ApiRequestBody代替@requestBody和@vaild
 *
 * @author liq
 * @date 2022/1/11 16:12
 */
@Component
public class CustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(ApiRequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        // todo 实现@vaild的参数校验功能
        Method method = methodParameter.getMethod();
        Class<?> parameterType = method.getParameterTypes()[0];
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
        InputStream inputStream = inputMessage.getBody();
        return JSONObject.parseObject(inputStream, parameterType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
