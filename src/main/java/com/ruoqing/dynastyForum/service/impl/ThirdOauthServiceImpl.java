package com.ruoqing.dynastyForum.service.impl;

import cn.hutool.core.lang.UUID;
import com.ruoqing.dynastyForum.api.QQApi;
import com.ruoqing.dynastyForum.component.QQComponent;
import com.ruoqing.dynastyForum.component.RedisService;
import com.ruoqing.dynastyForum.constant.RedisConstant;
import com.ruoqing.dynastyForum.entity.ThirdOauth;
import com.ruoqing.dynastyForum.mapper.ThirdOauthMapper;
import com.ruoqing.dynastyForum.service.IThirdOauthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.vo.QQAccessToken;
import com.ruoqing.dynastyForum.vo.QQUserInfO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Service
public class ThirdOauthServiceImpl extends ServiceImpl<ThirdOauthMapper, ThirdOauth> implements IThirdOauthService {

    private static final String RESPONSE_TYPE = "code";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String FMT = "json";

    @Resource
    private RedisService redisService;

    @Resource
    private QQApi qqApi;

    @Resource
    private QQComponent qqComponent;

    @Override
    public String qqUrl() {
        String state = UUID.fastUUID().toString();
        redisService.set(RedisConstant.QQ_STATE, state, 10, TimeUnit.MINUTES);
        return qqApi.authorize(qqComponent.getAppId(), RESPONSE_TYPE, qqComponent.getBackUrl(), state);
    }

    @Override
    public QQUserInfO qqLogin(String code, String state) {
        QQAccessToken accessToken = qqApi.getAccessToken(GRANT_TYPE, qqComponent.getAppId(), qqComponent.getAppKey(), code, qqComponent.getBackUrl(),
                FMT, 1);
        String openId = accessToken.getOpenId();
        ThirdOauth thirdOauth = lambdaQuery().eq(ThirdOauth::getOauthId, openId).one();
        return qqApi.getUserInfo(accessToken.getAccess_token(), qqComponent.getAppId(), openId);
    }

}
