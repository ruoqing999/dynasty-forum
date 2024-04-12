package com.ruoqing.dynastyForum.component;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.ruoqing.dynastyForum.constant.RedisConstant;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.util.JWTUtil;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
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

    public String createToken(UserInfoVO userInfo) {
        String uuid = UUID.fastUUID().toString();
        refreshToken(uuid, userInfo);
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(JWTUtil.USER_KEY, uuid);
        return JWTUtil.createToken(claimsMap);
    }

    private void refreshToken(String uuid, UserInfoVO userInfo) {
        redisService.set(getTokenKey(uuid), JSONUtil.toJsonStr(userInfo), expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String uuid) {
        return RedisConstant.LOGIN_USER_KEY + uuid;
    }
}
