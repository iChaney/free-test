package com.qiang.lock.queue;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author liq
 * @date 2021/7/1 10:21
 */
public class QueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<>(4);
        abq.add(1);
        abq.add(2);
        abq.add(3);
        abq.add(4);
        Iterator<Integer> iterator = abq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
