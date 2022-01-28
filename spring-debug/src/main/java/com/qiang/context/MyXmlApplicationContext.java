package com.qiang.context;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: liq
 * @date: 2021/4/19 22:39
 */
public class MyXmlApplicationContext extends ClassPathXmlApplicationContext {
    public MyXmlApplicationContext(String... configLocations) {
        super(configLocations);
    }

    /*public void initPropertySources() {
        getEnvironment().setRequiredProperties("abc");
    }*/

    /**
     * 自定义beanfactory
     */
    @Override
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
        this.setAllowCircularReferences(false);
        this.setAllowBeanDefinitionOverriding(false);
        super.customizeBeanFactory(beanFactory);
    }
}
