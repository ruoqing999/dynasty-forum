package com.ruoqing.dynastyForum.service.impl;

import cn.hutool.core.lang.UUID;
import com.ruoqing.dynastyForum.api.QQApi;
import com.ruoqing.dynastyForum.component.QQComponent;
import com.ruoqing.dynastyForum.component.RedisService;
import com.ruoqing.dynastyForum.constant.OauthEnum;
import com.ruoqing.dynastyForum.constant.RedisConstant;
import com.ruoqing.dynastyForum.entity.ThirdOauth;
import com.ruoqing.dynastyForum.mapper.ThirdOauthMapper;
import com.ruoqing.dynastyForum.service.IThirdOauthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.util.Assert;
import com.ruoqing.dynastyForum.vo.QQAccessToken;
import com.ruoqing.dynastyForum.vo.QQUserInfO;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
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
    private static final int NORMAL_RET = 0;

    @Resource
    private RedisService redisService;

    @Resource
    private QQApi qqApi;

    @Resource
    private QQComponent qqComponent;

    @Resource
    private IThirdOauthService thirdOauthService;

    @Override
    public String qqUrl() {
        String state = UUID.fastUUID().toString();
        redisService.set(RedisConstant.QQ_STATE + state, state, 10, TimeUnit.MINUTES);
        return qqApi.authorize(qqComponent.getAppId(), RESPONSE_TYPE, qqComponent.getBackUrl(), state);
    }

    @Override
    public UserInfoVO qqLogin(String code, String state) {
        String val = redisService.get(RedisConstant.QQ_STATE + state);
        Assert.isTrue(!Objects.equals(val, state), "QQ-State错误");

        QQAccessToken accessToken = qqApi.getAccessToken(GRANT_TYPE, qqComponent.getAppId(), qqComponent.getAppKey(),
                code, qqComponent.getBackUrl(), FMT, 1);
        String openId = accessToken.getOpenId();
        ThirdOauth thirdOauth = lambdaQuery().eq(ThirdOauth::getOauthId, openId).one();
        if (null == thirdOauth) {
            ThirdOauth save = new ThirdOauth();
            save.setOauthId(openId);
            save.setOauthType(OauthEnum.QQ.getCode());
            thirdOauthService.save(save);
        }
        QQUserInfO userInfo = qqApi.getUserInfo(accessToken.getAccess_token(), qqComponent.getAppId(), openId);
        Assert.isTrue(userInfo.getRet() != NORMAL_RET, "QQ-获取用户信息失败");
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        userInfoVO.setAvatarUrl(userInfo.getFigureurl_qq_1());
        userInfoVO.setOpenId(openId);
        userInfoVO.setOauthType(OauthEnum.QQ.getCode());
        return userInfoVO;
    }
}
