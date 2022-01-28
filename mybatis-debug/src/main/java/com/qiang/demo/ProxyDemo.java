package com.qiang.demo;


import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author liq
 * @date 2021/5/24 17:27
 */
interface A {
    void hello(String name);
}

@Data
public class ProxyDemo {
    public static void main(String[] args) {
        Object o = new Object();
        A a = (A)Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class<?>[]{A.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("hello, " + args);
                return method.invoke(o, args);
            }
        });
        a.hello("小明");
    }
}
