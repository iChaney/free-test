package com.qiang;

import com.qiang.compent.Teacher;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: liq
 * @date: 2021/4/19 22:14
 */
public class SpringTest {
    public static void main(String[] args) {
        //        AbstractApplicationContext applicationContext = new MyXmlApplicationContext("spring.xml");
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Teacher teacher = (Teacher)applicationContext.getBean("teacher");
    }
}
