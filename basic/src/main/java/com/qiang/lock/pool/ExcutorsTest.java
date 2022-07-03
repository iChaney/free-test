package com.qiang.lock.pool;

/**
 * @author liq
 * @date 2021/7/1 11:31
 */
public class ExcutorsTest {
    public static void main(String[] args) {
        getObject(new Integer(1));
    }

    public static  <T> void getObjectClass(T t){
        System.out.println(t.getClass());
    }
    public static  <T> void getObject(Object t){
        System.out.println(t.getClass());
    }

}
