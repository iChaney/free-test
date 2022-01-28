package com.qiang.ops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * @author liq
 * @date 2021/12/8 15:44
 */
@Service
public class HyperLogLogService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void batchAdd() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HyperLogLogOperations<String, String> hyperLog = stringRedisTemplate.opsForHyperLogLog();
        for (int i = 0; i <10000; i++) {
            hyperLog.add("student", "xiao"+i);
        }
        System.out.println(hyperLog.size("student"));
        stopWatch.stop();
        System.out.println("耗时"+stopWatch.getTotalTimeSeconds());       // 38秒
    }

    /**
     * 批量操作
     * 避免了网络传输的时间RTT
     */
    public void pipelined() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
                for (int i = 0; i <10000; i++) {
                    stringRedisConn.pfAdd("student", "big" + i);
                }
                return null;
            }
        });
        HyperLogLogOperations<String, String> hyperLog = stringRedisTemplate.opsForHyperLogLog();
        System.out.println(hyperLog.size("student"));
        stopWatch.stop();
        System.out.println("耗时"+stopWatch.getTotalTimeSeconds());   // 0.5秒
    }
}
