package com.qiang.lock;

/**
 * 创建一个对象需要3步 1. 申请堆内存 2. 初始化对象, 设置属性 3. 赋值对象指针 这3步有可能被指令重排
 *
 * @author liq
 * @date 2021/6/18 10:24
 */
public class CreateObjectTest {
    public static void main(String[] args) {
        String s = new String("a") + new String("b");
    }
}

class T {
    int m = 8;
}
