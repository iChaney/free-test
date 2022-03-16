package com.qiang.jvm;

/**
 * @author liq
 * @date 2022/3/1 17:27
 */
public class ClassB {
    public static void main(String[] args) {
        Father son = new Son();
        System.out.println(son.num);
        System.out.println(son.getVar());
    }
}
