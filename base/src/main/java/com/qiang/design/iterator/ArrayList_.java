package com.qiang.design.iterator;

/**
 * 集合在内存中的物理结构只有数组和链表两种
 *
 * @author liq
 * @date 2021/6/15 10:20
 */
public class ArrayList_<E> implements Collection_<E> {
    private int size = 0;
    private int currentIndex = 0;
    private int capacity = 10;
    private Object[] data;

    public ArrayList_() {
        capacity = 10;
        data = new Object[capacity];
    }

    public ArrayList_(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    @Override
    public void add(E e) {
        // 扩容
        if (currentIndex >= capacity) {
            refresh();
        }
        data[currentIndex] = e;
        currentIndex++;
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
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor <= size - 1;
        }

        @Override
        public E next() {
            E element = (E)data[cursor];
            cursor++;
            return element;
        }
    }

    private void refresh() {
        System.out.println("开始扩容...");
        Object[] tempData = new Object[capacity * 2];
        System.arraycopy(data, 0, tempData, 0, size);
        capacity = capacity * 2;
        data = tempData;
    }
}
