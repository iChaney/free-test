package com.qiang;

import com.qiang.bean.ImportBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author liq
 * @date 2021/4/23 10:32
 */
@SpringBootApplication
@Import(ImportBean.class)   // beanName为类全名
public class DebugAppliction {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DebugAppliction.class, args);
    }
}
