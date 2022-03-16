package com.qiang.io.bio;

import com.qiang.io.CustomConstant;
import com.qiang.io.CustomThreadPool;
import com.qiang.io.ResponseWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * QPS=180
 *
 * @author: liq
 * @date: 2021/7/1 22:51
 */
public class BioDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(CustomConstant.PORT, CustomConstant.BACKLOG);
        System.out.println("服务器启动了...");
        ThreadPoolExecutor threadPoolExecutor = CustomThreadPool.buildThreadPool();
        while (true) {
            Socket socket = serverSocket.accept();  // 阻塞
            threadPoolExecutor.execute(() -> {
                System.out.println(socket.getRemoteSocketAddress());
                OutputStream outputStream = null;
                try {
                    outputStream = socket.getOutputStream();
                    CustomThreadPool.doWork();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    bufferedWriter.write(ResponseWriter.buildResponse());   // 阻塞
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
