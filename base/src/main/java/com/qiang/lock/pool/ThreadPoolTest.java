package com.qiang.lock.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liq
 * @date 2021/6/22 18:24
 */
public class ThreadPoolTest {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        ThreadPoolExecutor tpe =
                new ThreadPoolExecutor(4, 10, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(200), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("my-thread");
                        return thread;
                    }
                });
        for (int i = 0; i < 214; i++) {
            tpe.execute(new SimpleRunable());
            System.out.printf("queue数量:%s%n", tpe.getQueue().size());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("一共运行了%s次任务", count);
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
            count.getAndIncrement();
        }
    }
}
