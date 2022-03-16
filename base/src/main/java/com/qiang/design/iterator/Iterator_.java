package com.qiang.design.iterator;

/**
 * 迭代器
 *
 * @author liq
 * @date 2021/6/15 11:30
 */
public interface Iterator_<E> {
    boolean hasNext();

    E next();
}
