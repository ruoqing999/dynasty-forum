package com.ruoqing.dynastyForum.controller;


import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.component.TokenService;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.ro.LoginRO;
import com.ruoqing.dynastyForum.ro.RegisterRO;
import com.ruoqing.dynastyForum.service.IAuthService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private IAuthService authService;

    @Resource
    private TokenService tokenService;


    @IgnoreAuth
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid LoginRO loginRO) {
        User loginUser = authService.login(loginRO);
        return Result.ok(tokenService.createToken(loginUser));
    }

    @IgnoreAuth
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody @Valid RegisterRO registerRO){
        return Result.ok(authService.register(registerRO));
    }

}
