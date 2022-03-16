package com.qiang.io.aio;

import com.qiang.io.CustomConstant;
import com.qiang.io.CustomThreadPool;
import com.qiang.io.ResponseWriter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

/**
 * qps=182
 *
 * @author: liq
 * @date: 2021/7/3 19:05
 */
public class AioDemo {
    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(CustomConstant.PORT), CustomConstant.BACKLOG);
        System.out.println("server is start...");
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                serverSocketChannel.accept(null, this);
                try {
                    CustomThreadPool.doWork();
                    byte[] bytes = ResponseWriter.buildResponse().getBytes(StandardCharsets.UTF_8);
                    result.write(ByteBuffer.wrap(bytes));
                    result.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("失败");
            }
        });
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
