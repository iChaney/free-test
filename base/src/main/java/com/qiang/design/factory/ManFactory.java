package com.qiang.design.factory;

/**
 * @author liq
 * @date 2021/6/9 14:05
 */
public class ManFactory extends AbstractPersonFactory {
    @Override
    Person createPerson() {
        return new Man();
    }

    @Override
    Clothes createClothes() {
        return new Trousers();
    }

    @Override
    Hair createHair() {
        return new ShortHair();
    }
}
