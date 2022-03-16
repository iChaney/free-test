package com.qiang.lock.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 核心线程先执行, 再加入队列, 队列满了最大线程立即执行, 如果不够就执行拒绝策略
 * 线程实际维护的线程总数 = 最大线程数, 队列不产生线程
 *
 * @author liq
 * @date 2021/6/22 18:24
 */
public class ThreadPoolTest {
    private final static AtomicInteger count = new AtomicInteger(0);
    static int testThreadNum = 210;
    static CountDownLatch latch = new CountDownLatch(testThreadNum);

    public static void main(String[] args) {
        ThreadPoolExecutor tpe =
                new ThreadPoolExecutor(4, 10, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(200), new MyThreadFactory());
        for (int i = 0; i < testThreadNum; i++) {
            tpe.execute(new SimpleRunable());
            System.out.printf("queue数量:%s%n", tpe.getQueue().size());
        }
        // 线程阻塞, 直至前面线程任务处理完毕
        try {
            latch.await(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("实际消费%s个线程", count);
        tpe.shutdown(); // 关闭线程池
    }

    static class SimpleRunable implements Runnable {
        @Override
        public void run() {
            // 保证任务的打印顺序
            synchronized (this.getClass()) {
                int index = count.incrementAndGet();
                latch.countDown();
                System.out.printf("线程名: %s, 第%d个任务, run....%n", Thread.currentThread().getName(), index);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThreadFactory implements ThreadFactory {
        private final AtomicLong threadIndex = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "my-thread-" + threadIndex.incrementAndGet());
            return thread;
        }
    }
}
