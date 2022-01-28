package com.qiang.config;

import com.qiang.annotations.ApiRequestBody;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义返回值解析器, 返回json, 参数名称为蛇形
 *
 * @author liq
 * @date 2022/1/11 16:02
 */
public class CustomHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Method method = returnType.getMethod();
        return method.getParameterAnnotations()[0][0].annotationType().isAssignableFrom(ApiRequestBody.class);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);   // true表示在response中处理完成
        HttpServletResponse httpServletResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(MyMessageConverter.stringToCamel(returnValue));
    }
}
