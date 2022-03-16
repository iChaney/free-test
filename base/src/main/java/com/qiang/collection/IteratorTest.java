package com.qiang.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liq
 * @date 2021/11/5 11:36
 */
public class IteratorTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        removeElement(list);
        list.forEach(integer -> System.out.println(integer));
    }

    private static void removeElement(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if(element.equals(4)){
                iterator.remove();
            }
        }
    }
}

