package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.LocalAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.ro.LoginRO;
import com.ruoqing.dynastyForum.ro.RegisterRO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
public interface ILocalAuthService extends IService<LocalAuth> {

    User login(LoginRO loginRO);

    boolean register(RegisterRO registerRO);

    boolean logout();

    boolean registerUser(Integer userId, String account, String password);

}
