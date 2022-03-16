package com.qiang.io.nio;

import com.qiang.io.CustomConstant;
import com.qiang.io.CustomThreadPool;
import com.qiang.io.ResponseWriter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * qps=10
 *
 * @author liq
 * @date 2021/7/2 15:34
 */
public class NioDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(CustomConstant.PORT), CustomConstant.BACKLOG);
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务已经启动...");
        while (true) {
            selector.select();  // 这里在没有请求的时候会阻塞
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {    // 对select进行遍历
                SelectionKey key = iterator.next();
                // 如果注册的服务器能被执行的话, 创建一个可返回的socket并加入到selector中, 稍后执行
                if (key.isAcceptable()) {
                    try {
                        ServerSocketChannel serverSocketCha = (ServerSocketChannel)key.channel();
                        SocketChannel accept = serverSocketCha.accept();
                        System.out.println(accept.getRemoteAddress());
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_WRITE);   // 将客户端注册到selector
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (key.isWritable()) { // 处理业务
                    SocketChannel client = (SocketChannel)key.channel();
                    CustomThreadPool.doWork();
                    byte[] bytes = ResponseWriter.buildResponse().getBytes(StandardCharsets.UTF_8);
                    client.write(ByteBuffer.wrap(bytes));
                    client.close();
                }
                iterator.remove();
            }
        }
    }
}
