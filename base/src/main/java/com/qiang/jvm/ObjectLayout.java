package com.qiang.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author liq
 * @date 2021/6/22 17:04
 */
public class ObjectLayout {
    public static void main(String[] args) {
        T t = new T();
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        /*while (true) {

        }*/
    }
}

class T {
    int a;
    String s = "222";
    long l = 33L;
    Object son = new Object();

    public void satHello(String name) {
        System.out.println(name);
    }
}
