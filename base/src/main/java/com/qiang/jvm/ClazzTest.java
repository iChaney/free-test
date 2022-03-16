package com.qiang.jvm;

/**
 * @author liq
 * @date 2022/2/10 11:15
 */
public class ClazzTest {

    public static void main(String[] args) {
        B b = new B();
    }

    /**
     * 父类的构造方法也会被执行
     */
    static class A {
        public A() {
            System.out.println("A");
        }
    }

    static class B extends A {
        public B() {
            System.out.println("B");
        }
    }
}
