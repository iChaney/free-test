package com.qiang.io.nio;

import com.qiang.io.CustomConstant;
import com.qiang.io.CustomThreadPool;
import com.qiang.io.ResponseWriter;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * 单线程用数组循环执行任务 ops=10
 *
 * @author: liq
 * @date: 2021/6/27 16:06
 */
public class NioSimpleDemo {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(CustomConstant.PORT), CustomConstant.BACKLOG);
        channel.configureBlocking(false);   // 设置为非阻塞
        List<SocketChannel> clients = new LinkedList<>();
        System.out.println("server is start...");
        while (true) {
            SocketChannel accept = channel.accept();    // 非阻塞, 没有连接就返回null, 也会浪费cpu资源
            if (accept != null) {
                System.out.println(accept.getRemoteAddress());
                accept.configureBlocking(false);
                clients.add(accept);    // 将客户端添加到集合
            }
            // 遍历处理时间
            for (SocketChannel client : clients) {
                CustomThreadPool.doWork();
                byte[] bytes = ResponseWriter.buildResponse().getBytes(StandardCharsets.UTF_8);
                client.write(ByteBuffer.wrap(bytes));
                client.close();
                clients.remove(client);
            }
        }
    }
}
