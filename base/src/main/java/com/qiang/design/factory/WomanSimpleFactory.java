package com.qiang.design.factory;

/**
 * 简单工厂
 *
 * @author liq
 * @date 2021/6/9 11:15
 */
public class WomanSimpleFactory {
    public Person create() {
        return new Woman();
    }
}
