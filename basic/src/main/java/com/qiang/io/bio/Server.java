package com.qiang.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author: liq
 * @date: 2021/6/26 15:34
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9091));
        System.out.println("server is created...");
        while (true) {
            Socket client = server.accept();    // 阻塞1
            System.out.println("有客户端连接了:" + client.getPort());
            try {
                while (true) {
                    byte[] bytes = new byte[1024];
                    int read = client.getInputStream().read(bytes);
                    if (read != -1) {
                        System.out.println("收到消息: " + new String(bytes, 0, read));
                    }
                    OutputStream outputStream = client.getOutputStream();
                    byte[] outBytes = ("\r\nreceive: " + new String(bytes)).getBytes(StandardCharsets.UTF_8);
                    outputStream.write(outBytes);    // 阻塞2
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
