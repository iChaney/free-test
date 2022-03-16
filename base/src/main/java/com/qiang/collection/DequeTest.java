package com.qiang.collection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author liq
 * @date 2022/3/8 10:57
 */
public class DequeTest {
    public static void main(String[] args) {
        Deque<Object> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        arrayDeque.addLast(3);
        arrayDeque.iterator().forEachRemaining(o -> System.out.println(o));
        System.out.println(arrayDeque.peekLast());


    }
}
