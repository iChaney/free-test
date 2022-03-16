package com.qiang.lock.cpu;

import java.util.concurrent.CountDownLatch;

/**
 * cpu的缓存一致机制 内存行的大小是64byte
 *
 * @author liq
 * @date 2021/6/21 16:18
 */
public class CacheLineTest {
    public static T[] arr = new T[2];
    static long count = 1_0000_0000L;

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) {
//        System.out.println(ClassLayout.parseInstance(arr[0]).toPrintable());
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < count; i++) {
                arr[0].x = i;
            }
            countDownLatch.countDown();
        });
        Thread t2 = new Thread(() -> {
            for (long i = 0; i < count; i++) {
                arr[1].x = i;
            }
            countDownLatch.countDown();
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));
    }
}

class T {
    private long p1, p2, p3, p4, p5, p6, p7;
    public volatile long x = 0L;    // x只会和自己的前后属性在一个缓存行, 因为一致性协议, 所以和其他缓存行不会同步
    private long p11, p12, p13, p14, p15, p16, p17;
}
