package com.qiang.io.rpc;

import com.alibaba.fastjson.JSON;

/**
 * @author: liq
 * @date: 2021/6/27 22:11
 */
public class JsonUtil {
    public static byte[] serialize(Object o) {
        return JSON.toJSONBytes(o);
    }

    public static Object deSerialize(byte[] bytes, Class clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
