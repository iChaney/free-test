package com.qiang.design.iterator;

/**
 * @author liq
 * @date 2021/6/15 10:37
 */
public class IteratorTest {
    public static void main(String[] args) {
        Collection_<String> list = new ArrayList_<>();
        for (int i = 0; i < 15; i++) {
            list.add("s" + i);
        }
        System.out.println(list.size());
        Iterator_<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
