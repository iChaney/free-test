package com.qiang.aop;

/**
 * @author liq
 * @date 2021/8/3 16:54
 */
public class MyRunnale {

    public void run() {
        int i = 1 / 0;
        System.out.println("MyRunnale运行完成!");
    }
}
