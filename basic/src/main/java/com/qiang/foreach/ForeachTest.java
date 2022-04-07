package com.qiang.foreach;

import cn.hutool.core.io.watch.WatchUtil;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: liq
 * @date: 2022/4/4 16:30
 */
public class ForeachTest {
    public static List<Integer> list = new ArrayList<>();

    static {
        for (int i = 0; i < 300000; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        ForeachTest test = new ForeachTest();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        test.parallelStream();

        stopWatch.stop();
        System.out.printf("%n总耗时:%d毫秒", stopWatch.getTotalTimeMillis());
    }

    /**
     * fori遍历方式
     *
     * 4560毫秒
     */
    public void fori() {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d ", i);
        }
    }

    /**
     * fore遍历方式
     * 4561毫秒
     */
    public void fore() {
        for (Integer i : list) {
            System.out.printf("%d ", i);
        }
    }

    /**
     * iterator遍历方式
     * 4751
     */
    public void iterator() {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.printf("%d ", iterator.next());
        }
    }

    /**
     * staream遍历方式
     * 4793
     */
    public void stream() {
        list.forEach(i -> System.out.printf("%d ", i));
    }

    /**
     * parallelStream遍历方式
     * 4506
     */
    public void parallelStream() {
        list.parallelStream().forEach(i -> System.out.printf("%d ", i));
    }

}
