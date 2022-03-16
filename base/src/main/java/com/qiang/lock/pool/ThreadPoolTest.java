package com.qiang.lock.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liq
 * @date 2021/6/22 18:24
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor tpe =
                new ThreadPoolExecutor(4, 210, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(200), new MyThreadFactory());
        for (int i = 0; i < 100; i++) {
            tpe.execute(new SimpleRunable());
            System.out.printf("queue数量:%s%n", tpe.getQueue().size());
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SimpleRunable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThreadFactory implements ThreadFactory {
        private final AtomicLong threadIndex = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "my-t2hread" + threadIndex.incrementAndGet());
            return thread;
        }
    }
}
