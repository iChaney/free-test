package com.qiang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author liq
 * @date 2022/2/11 14:39
 */
@Configuration
public class MvcConfiguration {

    /**
     * 容器中默认没有
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
