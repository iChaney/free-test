package com.qiang.lock.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liq
 * @date 2021/6/22 18:24
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor tpe =
                new ThreadPoolExecutor(4, 10, 3000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new MyThreadFactory(),
                                       new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < tpe.getMaximumPoolSize(); i++) {
            tpe.execute(new SimpleRunable());
            System.out.println(tpe.getQueue().size());
        }
    }
}

class SimpleRunable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run...");
        while (true) {
        }
    }
}

class MyThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "MyThread");
    }
}
