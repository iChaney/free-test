package com.qiang.custom;

import com.qiang.bean.OrderMapper$Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author liq
 * @date 2021/9/3 18:14
 */
public class MapperBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("orderMapper");
        beanDefinition.setBeanClassName(OrderMapper$Proxy.class.getName());
    }
}
