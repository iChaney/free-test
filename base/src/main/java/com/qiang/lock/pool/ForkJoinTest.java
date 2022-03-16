package com.qiang.lock.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author liq
 * @date 2021/7/1 11:32
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        /*int[] arr = obtainArray();
        int sum = Arrays.stream(arr).parallel().sum();*/
        ForkJoinPool pool = new ForkJoinPool(2);
        ForkJoinCal task = new ForkJoinCal(0, 1000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
    }

    private static int[] obtainArray() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = i;
        }
        return arr;
    }
}

class Ftask extends RecursiveTask<Integer> {
    private int[] arr;

    public Ftask(int[] arr, int from, int to) {
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        int middle = arr.length / 2;
        Ftask f1 = new Ftask(arr, 0, middle);
        Ftask f2 = new Ftask(arr, middle + 1, arr.length - 1);
        f1.fork();
        f2.fork();
        return f1.join() + f2.join();
    }
}

class ForkJoinCal extends RecursiveTask<Long> {
    private long start;
    private long end;
    private static final long THRESHOLD = 10000L;//临界值

    public ForkJoinCal(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinCal left = new ForkJoinCal(start, middle);
            left.fork();
            ForkJoinCal right = new ForkJoinCal(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
