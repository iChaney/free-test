package com.qiang.configuration;

import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author liq
 * @date 2022/2/9 17:09
 */
@Configuration
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
public class PageHelperConfig implements InitializingBean {
    // 加入pageHelper拦截器
    private final List<SqlSessionFactory> sqlSessionFactoryList;

    public PageHelperConfig(List<SqlSessionFactory> sqlSessionFactoryList) {
        this.sqlSessionFactoryList = sqlSessionFactoryList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            List<Interceptor> interceptors = configuration.getInterceptors();
            for (Interceptor interceptor : interceptors) {
                if(interceptor instanceof PageInterceptor) {
                }
            }
        }
    }
}
