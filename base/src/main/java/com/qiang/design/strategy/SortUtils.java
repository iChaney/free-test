package com.qiang.design.strategy;

import java.util.Comparator;

/**
 * 策略模式
 *
 * @author liq
 * @date 2021/6/8 10:36
 */
public class SortUtils {
    public static <T> int compare(T o1, T o2, Comparator<T> comparator) {
        int result = comparator.compare(o1, o2);
        return result;
    }

    public static void main(String[] args) {
        student s1 = new student("小米", 12);
        student s2 = new student("小李", 11);
        int compare = SortUtils.compare(s1, s2, new StudentComparator());
        System.out.println(compare);
    }

    static class student {
        String name;
        int age;

        public student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class StudentComparator implements Comparator<student> {
        @Override
        public int compare(student o1, student o2) {
            if (o1.age > o2.age) {
                return 1;
            }
            if (o1.age < o2.age) {
                return -1;
            }
            return 0;
        }
    }
}
