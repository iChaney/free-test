package com.qiang.design.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liq
 * @date 2021/6/11 17:30
 */
public class CglibProxy {
    public static void main(String[] args) {
        Car sourceCar = new Car();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Car.class);
        enhancer.setCallback(new MethodInterceptor() {
            // 参数里的第一个参数obj指的是代理对象, 而不是被代理对象
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before");
                Object ret = method.invoke(sourceCar, args);
                return ret;
            }
        });
        Car car = (Car)enhancer.create();
        car.move();
    }
}
