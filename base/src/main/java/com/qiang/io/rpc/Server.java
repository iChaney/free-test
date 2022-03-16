package com.qiang.io.rpc;

import com.qiang.io.rpc.biz.User;
import com.qiang.io.rpc.biz.UserService;
import com.qiang.io.rpc.biz.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: liq
 * @date: 2021/6/27 21:28
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9093));
        while (true) {
            Socket client = server.accept();
            System.out.println("client连接上了 " + client.getPort());
            new Thread(() -> {
                try {
                    InputStream inputStream = client.getInputStream();
                    byte[] bytes = new byte[1024];
                    inputStream.read(bytes);
                    RpcParam rpcParam = (RpcParam)JsonUtil.deSerialize(bytes, RpcParam.class);
                    byte[] ret = null;
                    // 执行服务
                    ret = doService(rpcParam, ret);
                    OutputStream outputStream = client.getOutputStream();
                    outputStream.write(ret);
                    outputStream.flush();
                    inputStream.close();
                    outputStream.close();
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static byte[] doService(RpcParam rpcParam, byte[] ret) {
        // todo 去服务注册表寻找对应服务和方法
        if (rpcParam.getClazzName().equals(UserService.class.getName())) {
            User user = new UserServiceImpl().getUserById(1);
            ret = JsonUtil.serialize(user);
        }
        return ret;
    }
}
