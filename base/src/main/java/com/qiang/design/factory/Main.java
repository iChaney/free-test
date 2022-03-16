package com.qiang.design.factory;

/**
 * @author liq
 * @date 2021/6/9 11:15
 */
public class Main {
    public static void main(String[] args) {
        /*Person person = new WomanSimpleFactory().create();
        person.sayHello();*/
        AbstractPersonFactory apf = new ManFactory();
        Person person = apf.createPerson();
        person.sayHello();
        Clothes clothes = apf.createClothes();
        clothes.dress();
        Hair hair = apf.createHair();
        hair.feature();
    }
}
