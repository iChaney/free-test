package com.qiang.design.prototype;

/**
 * 原型模式 深拷贝与浅拷贝: 对象里的属性如果是引用类型, 浅拷贝只拷贝引用
 *
 * @author liq
 * @date 2021/6/16 9:17
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Pencil pencil = new Pencil(20, "BLACK");
        Student student = new Student("小张", 10, pencil);
        Student student2 = (Student)student.clone();
        System.out.println(student == student2);
        student.age = 12;   // 基本类型是直接赋值, 修改原值不会更改新值
        System.out.println(student2.name + " " + student2.age);
        pencil.color = "BLUE";  // 引用类型是复制引用地址, 指向同一个对象
        System.out.println(student2.pencil.color + " " + student2.pencil.length);
        // 如果想要深拷贝, 需要对象里的引用类型也要实现cloneable接口
        Pencil pencil2 = (Pencil)pencil.clone();
        student2.pencil = pencil2;
        pencil.color = "YELLOW";
        System.out.println(student2.pencil.color + " " + student2.pencil.length);
    }

}

class Student implements Cloneable {
    String name;
    int age;
    Pencil pencil;

    public Student(String name, int age, Pencil pencil) {
        this.name = name;
        this.age = age;
        this.pencil = pencil;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Pencil implements Cloneable {
    int length;
    String color;

    public Pencil(int length, String color) {
        this.length = length;
        this.color = color;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
