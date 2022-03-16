package com.qiang.lock.cpu;

import java.util.concurrent.CountDownLatch;

/**
 * 测试程序并不是按照顺序执行的 一致性: 保证在单线程环境下结果的一致性
 *
 * @author liq
 * @date 2021/6/21 18:05
 */
public class NoOrderTest {
    public static int a = 0;
    public static int b = 0;
    public static int x = 0;
    public static int y = 0;

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            CountDownLatch countDownLatch = new CountDownLatch(2);
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
                countDownLatch.countDown();
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
                countDownLatch.countDown();
            });
            t1.start();
            t2.start();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (x == 0 && y == 0) {
                System.out.println(i);
                break;
            }
        }
    }
}
