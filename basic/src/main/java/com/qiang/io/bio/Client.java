package com.qiang.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: liq
 * @date: 2021/6/26 22:27
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 9091);
        OutputStream outputStream = client.getOutputStream();
        InputStream inputStream = client.getInputStream();
        while (true) {
            byte[] outBytes = new byte[1024];
            System.in.read(outBytes);
            outputStream.write(outBytes);
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.println("收到消息: " + new String(bytes));
        }
    }
}
