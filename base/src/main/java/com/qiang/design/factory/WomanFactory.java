package com.qiang.design.factory;

/**
 * @author liq
 * @date 2021/6/9 14:06
 */
public class WomanFactory extends AbstractPersonFactory {
    @Override
    Person createPerson() {
        return new Woman();
    }

    @Override
    Clothes createClothes() {
        return new Skirt();
    }

    @Override
    Hair createHair() {
        return new LongHair();
    }
}
