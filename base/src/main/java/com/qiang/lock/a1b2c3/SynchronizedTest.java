package com.qiang.lock.a1b2c3;

/**
 * @author liq
 * @date 2021/6/23 9:53
 */
public class SynchronizedTest {
    private static int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
    private static char[] b = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        Object o = new Object();
        t1 = new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                synchronized (o) {
                    System.out.print(a[i]);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == (a.length - 1)) {
                        o.notifyAll();
                    }
                }
            }
        }, "T1");
        t2 = new Thread(() -> {
            for (int i = 0; i < b.length; i++) {
                synchronized (o) {
                    System.out.print(b[i]);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == (b.length - 1)) {
                        o.notifyAll();
                    }
                }
            }
        }, "T2");
        t1.start();
        t2.start();
    }
}
