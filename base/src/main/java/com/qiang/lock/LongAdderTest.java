package com.qiang.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author liq
 * @date 2021/6/18 11:51
 */
public class LongAdderTest {
    LongAdder count = new LongAdder();

    public void count() {
        count.add(1);
    }

    public static void main(String[] args) {
        LongAdderTest longAdderTest = new LongAdderTest();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            threadList.add(new Thread(longAdderTest::count));
        }
        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(longAdderTest.count);
    }
}
