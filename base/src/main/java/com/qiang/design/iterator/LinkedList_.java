package com.qiang.design.iterator;

/**
 * 链表
 *
 * @author liq
 * @date 2021/6/15 10:21
 */
public class LinkedList_<E> implements Collection_<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator_<E> iterator() {
        return new Iterator();
    }

    private class Iterator implements Iterator_<E> {

        @Override
        public boolean hasNext() {
            return last == null || last.next == null;
        }

        @Override

        public E next() {
            return last.next.element;
        }
    }

    private class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E elemet, Node<E> next) {
            this.prev = prev;
            this.element = elemet;
            this.next = next;
        }
    }
}
