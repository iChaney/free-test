package com.qiang.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign, 记录请求日志
 *
 * @author liq
 * @date 2022/2/15 11:15
 */
@Configuration
public class LogConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
