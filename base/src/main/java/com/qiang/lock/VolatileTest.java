package com.qiang.lock;

/**
 * @author liq
 * @date 2021/6/18 10:38
 */
public class VolatileTest {
    volatile boolean flag = true;

    volatile int i = 0;

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.testCommandResorted();
    }

    /**
     * 测试指令重排
     */
    public void testCommandResorted() {
        for (int j = 0; j <1000; j++) {
            new Thread(() -> {
                i++;
            }).start();
        }
//        Thread.currentThread().yield();
        System.out.println(i);
    }

    public void testShared() {
        VolatileTest test = new VolatileTest();
        new Thread(test::m, "T1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 不加volatile无法被new的线程发现flag改变了
        test.flag = false;
    }

    void m() {
        System.out.println("m() start ...");
        while (flag) {

        }
        System.out.println("m() end ...");
    }

}
