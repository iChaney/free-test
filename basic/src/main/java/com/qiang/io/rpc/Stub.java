package com.qiang.io.rpc;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author: liq
 * @date: 2021/6/27 20:57
 */
public class Stub {
    private Stub() {
    }

    public static Object getStub(Class clazz) {
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String clazzName = clazz.getName();     // 接口名
                String methodName = method.getName();   // 方法名
                byte[] bytes = doInvoke(clazzName, methodName, args);
                return JsonUtil.deSerialize(bytes, method.getReturnType());
            }
        });
        return o;
    }

    private static byte[] doInvoke(String clazzName, String methodName, Object[] args) throws Exception {
        Socket client = new Socket("127.0.0.1", 9093);
        OutputStream outputStream = client.getOutputStream();
        RpcParam rpcParam = new RpcParam(clazzName, methodName, args);
        byte[] param = JsonUtil.serialize(rpcParam);
        outputStream.write(param);
        outputStream.flush();
        // 读取结果
        InputStream inputStream = client.getInputStream();
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        outputStream.close();
        client.close();
        return bytes;
    }
}
