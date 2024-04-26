package com.ruoqing.dynastyForum.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import com.ruoqing.dynastyForum.api.QQApi;
import com.ruoqing.dynastyForum.component.QQComponent;
import com.ruoqing.dynastyForum.component.RedisService;
import com.ruoqing.dynastyForum.constant.OauthEnum;
import com.ruoqing.dynastyForum.constant.RedisConstant;
import com.ruoqing.dynastyForum.constant.Whether;
import com.ruoqing.dynastyForum.entity.ThirdOauth;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.mapper.ThirdOauthMapper;
import com.ruoqing.dynastyForum.service.IThirdOauthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.service.IUserService;
import com.ruoqing.dynastyForum.util.Assert;
import com.ruoqing.dynastyForum.vo.QQAccessToken;
import com.ruoqing.dynastyForum.vo.QQUserInfO;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
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
@Slf4j
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
    private IUserService userService;

    @Override
    public String qqUrl() {
        String state = UUID.fastUUID().toString();
        String encodeUrl = URLEncoder.encode(qqComponent.getBackUrl(), CharsetUtil.CHARSET_GBK);
        redisService.set(RedisConstant.QQ_STATE + state, state, 10, TimeUnit.MINUTES);
        return String.format("%s?client_id=%s&response_type=%s&redirect_uri=%s&state=%s",
                qqComponent.getAuthorizeUrl(), qqComponent.getAppId(), RESPONSE_TYPE, encodeUrl, state);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoVO qqLogin(String code, String state) {
        String val = redisService.get(RedisConstant.QQ_STATE + state);
        Assert.isTrue(!Objects.equals(val, state), "QQ-State错误");

        String qqAccessToken = qqApi.getAccessToken(GRANT_TYPE, qqComponent.getAppId(), qqComponent.getAppKey(),
                code, qqComponent.getBackUrl(), FMT, Whether.YES);
        QQAccessToken accessToken = JSONUtil.toBean(qqAccessToken, QQAccessToken.class);
        String openId = accessToken.getOpenid();
        String qqUserInfo = qqApi.getUserInfo(accessToken.getAccess_token(), qqComponent.getAppId(), openId);
        log.info("qqUserInfo:{}", qqUserInfo);
        QQUserInfO userInfo = JSONUtil.toBean(qqUserInfo, QQUserInfO.class);
        Assert.isTrue(userInfo.getRet() != NORMAL_RET, "QQ-获取用户信息失败");

        ThirdOauth thirdOauth = lambdaQuery().eq(ThirdOauth::getOauthId, openId).one();
        if (null == thirdOauth) {

            User user = new User();
            user.setNickName(userInfo.getNickname());
            user.setAvatar(userInfo.getFigureurl_qq_2());
            userService.save(user);

            ThirdOauth saveThirdOauth = new ThirdOauth();
            saveThirdOauth.setOauthId(openId);
            saveThirdOauth.setOauthType(OauthEnum.QQ.getCode());
            saveThirdOauth.setUserId(user.getUserId());
            save(saveThirdOauth);
            thirdOauth = saveThirdOauth;
        }

        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        userInfoVO.setNickName(userInfo.getNickname());
        userInfoVO.setAvatarUrl(userInfo.getFigureurl_qq_2());
        userInfoVO.setOauthType(OauthEnum.QQ.getCode());
        userInfoVO.setUserId(thirdOauth.getUserId());
        return userInfoVO;
    }
}
