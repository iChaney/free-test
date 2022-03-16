package com.qiang.custom;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liq
 * @date 2021/9/9 17:10
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {
    private Class<T> interfaceType;

    public MapperFactoryBean(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() throws Exception {
        Object o =
                Proxy.newProxyInstance(MapperFactoryBean.class.getClassLoader(), new Class[]{interfaceType}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (Object.class.equals(method.getDeclaringClass())) {
                            System.out.println("开启事务...");
                            Object ret = method.invoke(this, args);
                            System.out.println("结束事务...");
                            return ret;
                        } else {
                            return null;
                        }
                    }
                });
        return (T)o;
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }
}
