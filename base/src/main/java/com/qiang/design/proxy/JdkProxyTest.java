package com.qiang.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk实现的动态代理, 基于asm asm可以生成class
 *
 * @author liq
 * @date 2021/6/11 15:46
 */
public class JdkProxyTest {
    // todo 学一下ASM动态修改class
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Movable car = new Car();
        Movable proxy = (Movable)Proxy.newProxyInstance(car.getClass().getClassLoader(), new Class[]{Movable.class}, new InvocationHandler() {
            // 参数里的第一个参数obj指的是代理对象, 而不是被代理对象
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("move")) {
                    System.out.println("before");
                    Object ret = method.invoke(car, args);
                    System.out.println("after");
                    return ret;
                }
                return null;
            }
        });
        proxy.move();
    }
}
