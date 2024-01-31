package com.ruoqing.dynastyForum.component;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

}
