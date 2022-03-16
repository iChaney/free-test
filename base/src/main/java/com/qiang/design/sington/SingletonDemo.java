package com.qiang.design.sington;

/**
 * 单例模式 1. 先将构造方法私有化 2. 保证全局只有一个类只有一个实例
 * <p>
 * <p>
 * <p>
 * 静态内部类的方式比较简单常用
 *
 * @author liq
 * @date 2021/5/26 17:25
 */
public class SingletonDemo {
    public static SingletonDemo getInstance() {
        return Inner.instance;
    }

    private SingletonDemo() {
    }

    private static class Inner {
        private static final SingletonDemo instance = new SingletonDemo();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                System.out.println(SingletonDemo.getInstance());
            }).start();
        }
    }


}
