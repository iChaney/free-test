package com.qiang.ops;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author liq
 * @date 2021/12/10 10:17
 */
@Service
public class StringService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    public void setValues(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        String vvv = stringRedisTemplate.opsForValue().get(key);
        System.out.println(vvv);
    }

    /**
     * 删除门店活动
     */
    public void deleteHotelActivity(String... hotelNos) {
        for (String hotelNo : hotelNos) {
            if(StringUtils.isEmpty(hotelNo)) {
                continue;
            }
            Collection<String> activitiyKeys = stringRedisTemplate.keys(String.format("sjz::hotel::promotionPackage::%s::*", hotelNo));
            if(!CollectionUtils.isEmpty(activitiyKeys)) {   // 批量删除用流提升速度
                stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
                    @Override
                    public Object doInRedis(RedisConnection connection) throws DataAccessException {
                        StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
                        for (String activitiyKey : activitiyKeys) {
                            stringRedisConn.del(activitiyKey);
                        }
                        System.out.println(String.format("门店%s 删除%s个key", hotelNo, activitiyKeys.size()));
                        return null;
                    }
                });
            }else {
                System.out.println(String.format("门店缓存为空, 门店%s", hotelNo));
            }

        }
    }
}
