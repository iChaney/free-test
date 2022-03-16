package com.qiang.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liq
 * @date 2021/7/22 17:18
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<ObjectFactory> list = new ArrayList<>();
        list.add(() -> getObject222());
        for (ObjectFactory factory : list) {
            System.out.println(factory.getObject());
        }
    }

    static Object getObject222() {
        return new Object();
    }

}

interface ObjectFactory<T> {
    T getObject();
}
