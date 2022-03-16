package com.qiang.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liq
 * @date 2021/6/23 16:35
 */
public class PrintGcTest {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        for (; ; ) {
            byte[] arr = new byte[1024 * 1024];
            list.add(arr);
        }
    }

}

