package com.qiang.aop;

/**
 * @author liq
 * @date 2021/8/3 16:58
 */
public class MyAspact {

    public void doBefore() {
        System.out.println("【AOP】aop的前置方法执行完毕");
    }

    public void doAfter() {
        System.out.println("【AOP】aop的后置方法执行完毕");
    }

    public void doThrow(RuntimeException runtimeException) {
        System.out.println("【AOP】aop的afterThrow方法执行完毕");
    }
}
