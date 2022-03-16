package com.qiang.design.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式 目的: 减少系统中相同对象的数量
 *
 * @author liq
 * @date 2021/6/11 9:26
 */
public class FlyweightTest {
    public static void main(String[] args) {
        Shape shape1 = ShapeFactory.getShape("1");
        Shape shape11 = ShapeFactory.getShape("1");
        Shape shape3 = ShapeFactory.getShape("3");
        System.out.println(shape1.equals(shape11));
    }
}

class Shape {
    String color;
    String size;

    public Shape(String color, String size) {
        this.color = color;
        this.size = size;
    }
}

class Circle extends Shape {
    public Circle(String color, String size) {
        super(color, size);
    }
}

class ShapeFactory {
    static Map<String, Shape> shapes = new HashMap<>();

    public static Shape getShape(String size) {
        if (shapes.get(size) != null) {
            System.out.println("从缓存中获取 " + size);
            return shapes.get(size);
        }
        Shape shape = new Shape("BLUE", size);
        shapes.put(size, shape);
        return shape;
    }
}
