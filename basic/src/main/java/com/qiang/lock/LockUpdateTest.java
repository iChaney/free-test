package com.qiang.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 测试锁升级, 对象头里偏向锁的变化
 *
 * @author liq
 * @date 2021/6/18 9:43
 */
public class LockUpdateTest {
    public static void main(String[] args) {
        // jvm启动时检测默认4秒会加偏向锁
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();
        // 无锁
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // 只有1条线程时, 锁升级为轻量锁 thin lock   (jvm知道有几条线程在竞争这个对象的锁)
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        // 有多条线程竞争时, 升级为重量锁 fat lock
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                synchronized (o) {
                    System.out.println(ClassLayout.parseInstance(o).toPrintable());
                }
            }).start();
        }

        /*for (int i = 0; i < 11; i++) {
            new Thread(() -> {
                synchronized (o) {
                    System.out.println(ClassLayout.parseInstance(o).toPrintable());
                }
            }).start();
        }*/
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

}
