package com.qiang.datatype;

import java.math.BigDecimal;

/**
 * @author: liq
 * @date: 2022/4/3 21:28
 */
public class DoubleTest {
    public static void main(String[] args) {
        DoubleTest doubleTest = new DoubleTest();
        doubleTest.parseData();
    }

    public void parseData() {
        Double d = new Double(3.1);
        Float f = new Float(3.1);
        long d1 = Double.doubleToRawLongBits(d);
        int f1 = Float.floatToRawIntBits(f);
        System.out.println(Long.toBinaryString(d1));
        System.out.println(Integer.toBinaryString(f1));
    }

    public void floatAndDouble () {
        float a = 2.0f;
        double b = 2.00;
        System.out.println(a==b);   // float会自动向上转型
    }

    /**
     * double的三种比较方式
     */
    public void compareDouble() {
        Double d1 = new Double("3");
        Double d2 = new Double("3.0");
        System.out.println(d1==d2);
        System.out.println(d1.equals(d2));
        System.out.println(d1.compareTo(d2));
    }

}
