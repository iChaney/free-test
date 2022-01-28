package com.qiang.config;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liq
 * @date 2022/1/17 11:42
 */
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setCharacterEncoding("utf-8");
        String result = "";
        if(ex instanceof RuntimeException) {
            result = ex.getMessage();
        }else if (ex instanceof IllegalArgumentException) {
            result = ex.getMessage();
        }
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
