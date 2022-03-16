package com.qiang.io.rpc;

import java.io.Serializable;

/**
 * @author: liq
 * @date: 2021/6/27 22:17
 */
public class RpcParam implements Serializable {
    private String clazzName;
    private String methodName;
    private Object[] args;

    public RpcParam(String clazzName, String methodName, Object[] args) {
        this.clazzName = clazzName;
        this.methodName = methodName;
        this.args = args;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
