package com.qiang.config;

import com.qiang.custom.MapperImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liq
 * @date 2021/9/14 17:54
 */
@Configuration
@Import(MapperImportBeanDefinitionRegistrar.class)
public class AppConfig {
}
