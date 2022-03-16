package com.qiang.io;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liq
 * @date 2021/7/2 11:18
 */
public class CustomThreadPool {
    private CustomThreadPool() {
    }

    public static ThreadPoolExecutor buildThreadPool() {
        return new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void doWork() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
