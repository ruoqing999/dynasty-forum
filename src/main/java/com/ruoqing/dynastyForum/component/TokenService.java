package com.ruoqing.dynastyForum.component;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.ruoqing.dynastyForum.constant.RedisConstant;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.util.JWTUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TokenService {

    private final static long expireTime = 720;


    @Resource
    private RedisService redisService;

    public String createToken(User user) {
        String uuid = UUID.fastUUID().toString();
        refreshToken(uuid, user);
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(JWTUtil.USER_KEY, uuid);
        return JWTUtil.createToken(claimsMap);
    }

    private void refreshToken(String uuid, User user) {
        redisService.set(getTokenKey(uuid), JSONUtil.toJsonStr(user), expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String uuid) {
        return RedisConstant.LOGIN_USER_KEY + uuid;
    }
}
