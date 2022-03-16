package com.qiang.lock.a1b2c3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序打印出A1B2C3D4...Z26
 *
 * @author liq
 * @date 2021/6/18 16:30
 */
public class ReentrantLockTest {
    static volatile AtomicInteger index = new AtomicInteger();

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        ReentrantLock lock = new ReentrantLock(true);
        Thread thread1 = new Thread(() -> test.m1(lock));
        Thread thread2 = new Thread(() -> test.m2(lock));
        thread1.start();
        thread2.start();
    }

    public void m1(Lock lock) {
        for (int i = 0; i < 26; i++) {
            try {
                lock.lock();
                //                delay(100);
                System.out.println((i + 1));
            } finally {
                lock.unlock();
            }
        }
    }

    public void m2(Lock lock) {
        for (int i = 0; i < 26; i++) {
            try {
                lock.lock();
                //                delay(100);
                System.out.println((char)(65 + i));
            } finally {
                lock.unlock();
            }
        }
    }

    public void delay(int timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
