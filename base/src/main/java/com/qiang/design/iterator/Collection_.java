package com.qiang.design.iterator;

/**
 * @author liq
 * @date 2021/6/15 10:21
 */
public interface Collection_<E> {
    void add(E e);

    int size();

    Iterator_<E> iterator();
}
