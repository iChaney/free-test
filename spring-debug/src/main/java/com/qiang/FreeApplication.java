package com.qiang;

import com.qiang.aop.MyRunnale;
import com.qiang.bean.OrderMapper;
import com.qiang.bean.SimpleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: liq
 * @date: 2021/4/25 21:19
 */
public class FreeApplication {
    public static void main(String[] args) {
        //        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.qiang");
        //        getMapper(applicationContext);
        System.out.println(applicationContext.getBean("x"));
        System.out.println(applicationContext.getBean("y"));
    }

    private static void getMapper(AnnotationConfigApplicationContext applicationContext) {
        OrderMapper orderMapper = applicationContext.getBean("orderMapper", OrderMapper.class);
        System.out.println(orderMapper);
        System.out.println(applicationContext.getBean("userMapper"));
    }

    static void testSimpleBean(ApplicationContext applicationContext) {
        SimpleBean simpleBean = applicationContext.getBean("simpleBean", SimpleBean.class);
        System.out.println(simpleBean.getName());
    }

    /**
     * 测试aop
     */
    static void testAop(ApplicationContext applicationContext) {
        MyRunnale myRunnale = (MyRunnale)applicationContext.getBean("myRunnale");
        myRunnale.run();
    }
}
