package com.qiang.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 读的时候可以一起读, 共享锁 写的时候只能一个一个读, 排它锁
 *
 * @author liq
 * @date 2021/6/18 15:11
 */
public class ReadWriteLockTest {
    //    public static Lock lock = new ReentrantLock();
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    public static Lock readLock = lock.readLock();
    public static Lock writeLock = lock.writeLock();

    public void r(Lock lock) {
        try {
            lock.lock();
            delay(1000);
            System.out.println("read...");
        } finally {
            lock.unlock();
        }
    }

    public void w(Lock lock) {
        try {
            lock.lock();
            delay(1000);
            System.out.println("write...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockTest test = new ReadWriteLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> test.r(readLock)).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> test.w(writeLock)).start();
        }
    }

    public void delay(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
