package com.qiang.mq.simple;

import cn.hutool.core.io.watch.WatchUtil;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: liq
 * @date: 2022/3/12 13:42
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(finalI);
                if(finalI % 5 == 0) {
                    try {
                        System.out.println(finalI+"开始等待");
                        Thread.sleep(8000);
                        System.out.println(finalI+"等待8秒完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                latch.countDown();
            }).start();
        }
//        Thread.currentThread().join(5);
        // 主线程等待3秒后, 不再阻塞
        boolean await = latch.await(3000, TimeUnit.MILLISECONDS);
        stopWatch.stop();
        System.out.println(String.format("共消耗%d秒", stopWatch.getTotalTimeMillis()));
    }
}
