package com.qiang.design.proxy;

/**
 * @author liq
 * @date 2021/6/11 15:42
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        new CarProxy(new Car()).move();
    }
}

interface Movable {
    void move();

    void stop();
}

class Car implements Movable {
    @Override
    public void move() {
        System.out.println("car is moving...");
    }

    @Override
    public void stop() {
        System.out.println("car is stopping ...");
    }
}

class CarProxy {
    private Movable movable;

    public CarProxy(Movable movable) {
        this.movable = movable;
    }

    public void move() {
        System.out.println("before");
        movable.move();
        System.out.println("after");
    }
}
