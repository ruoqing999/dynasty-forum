package com.ruoqing.dynastyForum.service;

import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.ro.LoginRO;
import com.ruoqing.dynastyForum.ro.RegisterRO;

public interface IAuthService {
    User login(LoginRO loginRO);

    boolean register(RegisterRO registerRO);

    boolean logout();

}
