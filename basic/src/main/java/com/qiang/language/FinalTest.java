package com.qiang.language;

/**
 * 只要有finally,无论有无异常都会返回finally代码块的东西
 *
 * @author liq
 * @date 2022/4/26 22:07
 */
public class FinalTest {
    public static void main(String[] args) {
        System.out.println(test1());
    }

    public static int test1() {
        try{
            int i = 1/0;
            return 1;
        }catch (Exception e) {
            return 2;
        }finally {
            return 3;
        }
    }
}
