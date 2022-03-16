package com.qiang.io.nio;

import java.nio.ByteBuffer;

/**
 * @author liq
 * @date 2021/7/2 14:11
 */
public class ByteBufferDemo {
    public static void main(String[] args) {
        // 一个int等于4个字节
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(1);
        buffer.putInt(2);
        buffer.putInt(3);
        System.out.println(buffer);
        buffer.flip();  // 翻转 position=0, limit=有值的最大索引位
        System.out.println(buffer);
        int anInt = buffer.getInt();
        System.out.println(anInt);
        System.out.println(buffer);
        System.out.println(buffer.remaining());
        buffer.mark();  // 记录标记位
        int anInt1 = buffer.getInt();
        int anInt2 = buffer.getInt();
        System.out.println(anInt1);
        System.out.println(anInt2);
        System.out.println(buffer);
        buffer.reset(); // position移动到mark位
        System.out.println(buffer);
        buffer.clear();
        System.out.println(buffer);
    }
}
