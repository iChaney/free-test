package com.qiang.io.netty;

import java.nio.ByteBuffer;

/**
 * @author liq
 * @date 2021/7/9 18:05
 */
public class ZeroCopyDemo {
    static int capacity = 1024_000; // 1k

    public static void main(String[] args) {
        heapCopy();
        directCopy();
    }

    private static void heapCopy() {
        long start = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.put((byte)i);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("heap耗时:%s毫秒", end - start));
    }

    private static void directCopy() {
        long start = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocateDirect(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.put((byte)i);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("direct耗时:%s毫秒", end - start));
    }
}
