package com.ruoqing.dynastyForum.config.Interceptor;

import cn.hutool.json.JSONUtil;
import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.component.RedisService;
import com.ruoqing.dynastyForum.constant.RedisConstant;
import com.ruoqing.dynastyForum.constant.ResultConstant;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.handler.exception.AuthorizationException;
import com.ruoqing.dynastyForum.util.JWTUtil;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    private final RedisService redisService;

    public AuthenticationInterceptor(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object object) {

        if (true) {
            return true;
        }

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(IgnoreAuth.class)) {
            return true;
        }

        String token = req.getHeader("token");
        if (ObjectUtils.isEmpty(token))
            throw new AuthorizationException(ResultConstant.AUTHORIZATION_ERROR.getMessage());
        //验证token
        String userKey = JWTUtil.getUserKey(token);
        String userStr = redisService.get(RedisConstant.LOGIN_USER_KEY + userKey);
        if (ObjectUtils.isEmpty(userStr))
            throw new AuthorizationException(ResultConstant.AUTHORIZATION_ERROR.getMessage());
        UserInfoVO userInfoVO = JSONUtil.toBean(userStr, UserInfoVO.class);
        userInfoVO.setUserKey(userKey);
        UserContext.set(userInfoVO);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.remove();
    }
}
