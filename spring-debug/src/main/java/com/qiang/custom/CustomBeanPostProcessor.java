package com.qiang.custom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author liq
 * @date 2021/7/29 10:57
 */
public class CustomBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format("================ process before bean: %s, class: %s", beanName, bean.getClass().getName()));
        // 这里可以做动态代理
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format("================== process after bean: %s, class: %s", beanName, bean.getClass().getName()));
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

}
