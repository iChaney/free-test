package com.qiang.design.factory;

/**
 * 抽象工厂
 *
 * @author liq
 * @date 2021/6/9 11:36
 */
public abstract class AbstractPersonFactory {
    abstract Person createPerson();

    abstract Clothes createClothes();

    abstract Hair createHair();
}
