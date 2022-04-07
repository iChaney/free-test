package com.qiang.lock;

/**
 * 重入锁，指的是以线程为单位，当一个线程获取对象锁之后，这个线程可以再次获取本对象上的锁，而其他的线程是不可以的。
 *
 * @author: liq
 * @date: 2022/3/18 22:19
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.add();
        lock.unlock();
    }

    static abstract class Lock {
        abstract void lock();
        abstract void unlock();
        abstract void add();
    }
    /**
     * 不能重入的锁
     *
     * @author: liq
     * @date: 2022/3/18 22:33
     */
    static class SimpleLock extends Lock {

        private boolean isLock = false;

        public void lock() {
            if(isLock) {
                try {
                    System.out.printf("线程%s正在获取锁, 正在等待...%n", Thread.currentThread().getName());
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLock = true;
            System.out.printf("线程%s上锁成功%n", Thread.currentThread().getName());
        }

        public void unlock() {
            isLock = false;
        }

        public void add() {
            lock();
            unlock();
        }
    }

    static class ReentrantLock extends Lock {

        private boolean isLock = false;
        private int times = 0;
        private Thread currentThread = null;

        public void lock() {
            // 不是当前持有锁的线程才去获取锁
            if(isLock && !currentThread.equals(Thread.currentThread())) {
                try {
                    System.out.printf("线程%s正在获取锁, 正在等待...%n", Thread.currentThread().getName());
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLock = true;
            currentThread = Thread.currentThread();
            times++;
            System.out.printf("线程%s上锁成功%n", Thread.currentThread().getName());
        }

        public void unlock() {
            times--;
            if(times == 0 && currentThread.equals(Thread.currentThread())) {
                isLock = false;
                System.out.printf("线程%s解锁成功%n", Thread.currentThread().getName());
            }
        }

        public void add() {
            lock();
            unlock();
        }
    }

}
