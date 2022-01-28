package com.qiang.configuration;

import com.qiang.config.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.List;

/**
 * @author liq
 * @date 2022/1/7 18:14
 */
@Configuration
@EnableConfigurationProperties(CarProperties.class)
public class MyMVCConfig {

//    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
        servletServletRegistrationBean.setServlet(new Myservlet());
        servletServletRegistrationBean.setUrlMappings(Arrays.asList("/my/*"));
        return servletServletRegistrationBean;
    }
    /**
     * 配置webMVC就用这个类
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * 自定义内容转换器
             */
            /*@Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MyMessageConverter());
            }*/

            /**
             * 自定义返回值和参数解析器
             */
            @Override
            public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
                handlers.add(new CustomHandlerMethodReturnValueHandler());
            }

            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new CustomHandlerMethodArgumentResolver());
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new IpHandlerInterceptor());
            }

            @Override
            public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//                resolvers.add(new CustomHandlerExceptionResolver());
            }
        };
    }
}
