package com.ruoqing.dynastyForum.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.ruoqing.dynastyForum.common.UserContext;
import com.ruoqing.dynastyForum.component.RedisService;
import com.ruoqing.dynastyForum.constant.RedisConstant;
import com.ruoqing.dynastyForum.constant.ResultConstant;
import com.ruoqing.dynastyForum.entity.LocalAuth;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.mapper.LocalAuthMapper;
import com.ruoqing.dynastyForum.ro.LoginRO;
import com.ruoqing.dynastyForum.ro.RegisterRO;
import com.ruoqing.dynastyForum.service.ILocalAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoqing.dynastyForum.service.IUserService;
import com.ruoqing.dynastyForum.util.Assert;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;



/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Service
public class LocalAuthServiceImpl extends ServiceImpl<LocalAuthMapper, LocalAuth> implements ILocalAuthService {

    @Resource
    private IUserService userService;

    @Resource
    private RedisService redisService;

    @Override
    public User login(LoginRO loginRO) {
        var account = loginRO.getAccount();
        LocalAuth localAuth = lambdaQuery().eq(LocalAuth::getAccount, account).one();
        Assert.isTrue(null == localAuth, ResultConstant.LOGIN_ERROR.getMessage());
        var password = loginRO.getPassword();
        String dbPassword = localAuth.getPassword();
        Assert.isTrue(!DigestUtil.bcryptCheck(password, dbPassword), ResultConstant.LOGIN_ERROR.getMessage());
        return userService.getById(localAuth.getUserId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(RegisterRO registerRO) {
        var account = registerRO.getAccount();
        boolean accountExist = lambdaQuery().eq(LocalAuth::getAccount, account).exists();
        Assert.isTrue(accountExist, ResultConstant.REGISTER_ERROR.getMessage());
        var nickName = registerRO.getNickName();
        User registerUser = new User();
        registerUser.setNickName(StringUtils.hasText(nickName) ? nickName : account);
        userService.save(registerUser);

        var password = registerRO.getPassword();
        return registerUser(registerUser.getUserId(), account, password);
    }

    @Override
    public boolean logout() {
        UserInfoVO userInfoVO = UserContext.get();
        return redisService.del(RedisConstant.LOGIN_USER_KEY + userInfoVO.getUserKey());
    }

    @Override
    public boolean registerUser(Integer userId, String account, String password) {
        LocalAuth localAuth = new LocalAuth();
        localAuth.setUserId(userId);
        localAuth.setAccount(account);
        localAuth.setPassword(DigestUtil.bcrypt(password));
        return save(localAuth);
    }
}
