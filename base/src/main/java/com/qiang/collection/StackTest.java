package com.qiang.collection;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 模拟堆栈
 *
 * @author liq
 * @date 2021/7/20 11:38
 */
public class StackTest {
    public static void main(String[] args) {
        //        Stack<Integer> stack = new LinkedListStack<>();
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

interface Stack<E> {
    void push(E e);

    E pop();
}

class ArrayListStack<E> implements Stack<E> {
    private List<E> list = new ArrayList<>();
    private int cursor = -1;

    public void push(E e) {
        list.add(e);
        cursor++;
    }

    public E pop() {
        if (cursor < 0) {
            throw new RuntimeException("stack没有元素");
        }
        E e = list.get(cursor);
        cursor--;
        return e;
    }
}

/**
 * linkedlist实现了deque
 */
class LinkedListStack<E> implements Stack<E> {
    private Deque<E> deque = new LinkedList<>();

    @Override
    public void push(E e) {
        deque.push(e);
    }

    @Override
    public E pop() {
        return deque.pop();
    }
}
