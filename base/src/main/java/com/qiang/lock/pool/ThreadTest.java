package com.qiang.lock.pool;

/**
 * @author liq
 * @date 2021/7/1 14:14
 */
public class ThreadTest {

    public static void main(String[] args) {
        intercept();
    }

    private static void intercept() {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 is start");
            try {
                Thread.sleep(100 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 is over");
        }, "T1");
        t1.start();
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt(); // 线程在阻塞状态时才会跑出中断异常
        System.out.println("main is over");
    }

    private static void yeild() {
        MyTask myTask = new MyTask();
        Thread thread1 = new Thread(myTask, "T1");
        Thread thread2 = new Thread(myTask, "T2");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main is run");
    }


    /**
     * join
     */
    private static void join() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "run...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1");
        thread.start();
        try {
            thread.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main run over");
    }
}

class MyTask implements Runnable {
    private Object o = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (o) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Thread.currentThread().getName().equals("T1")) {
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName() + " ----- " + i);
            }
        }
    }
}
