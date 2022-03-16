package com.qiang.design.sington;

/**
 * @author liq
 * @date 2021/6/22 11:22
 */
public class DCL {
    private volatile static DCL t = null;

    private DCL() {
        // 构造器必须私有化
    }

    public static DCL getInstance() {
        if (t == null) {
            synchronized (DCL.class) {
                if (t == null) {
                    t = new DCL();  // volatile主要是为了防止指令重排
                }
            }
        }
        return t;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(DCL.getInstance());
            }).start();
        }
    }

}
