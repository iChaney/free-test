package com.qiang.lock.a1b2c3;

/**
 * @author: liq
 * @date: 2021/6/22 23:12
 */
public class LockSupportTest {
    private static final int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
    private static final char[] b = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < b.length; i++) {
                System.out.println(b[i]);
            }
        });
        t1.start();
        t2.start();
    }
}
