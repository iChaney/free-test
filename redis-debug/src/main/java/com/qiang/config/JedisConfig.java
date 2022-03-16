package com.qiang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author liq
 * @date 2021/12/2 14:59
 */
@Configuration
public class JedisConfig {
    @Bean("jedisConnectionFactory")
    public JedisConnectionFactory redisConnectionFactory() {
//         1. 单列
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("106.14.68.199", 6379);    // 生产环境
        config.setPassword("myredis");
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("120.92.164.230", 6380);  // 测试环境
//         2.哨兵模式
//        RedisSentinelConfiguration config =  new RedisSentinelConfiguration().master("mymaster").sentinel("120.92.164.230", 26379);
        // 3.集群模式
//        RedisClusterConfiguration config = new RedisClusterConfiguration().clusterNode("120.92.164.230", 7000);
        //        config.setPassword("root");
        return new JedisConnectionFactory(config);
    }
    /*@Bean("stringRedisTemplate")
    public StringRedisTemplate getStringRedisTemplate(@Autowired JedisConnectionFactory jedisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        return stringRedisTemplate;
    }*/

}
