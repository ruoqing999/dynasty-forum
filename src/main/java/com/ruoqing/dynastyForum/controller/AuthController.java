package com.ruoqing.dynastyForum.controller;


import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.component.TokenService;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.ro.LoginRO;
import com.ruoqing.dynastyForum.ro.RegisterRO;
import com.ruoqing.dynastyForum.service.ILocalAuthService;
import com.ruoqing.dynastyForum.service.IThirdOauthService;
import com.ruoqing.dynastyForum.vo.QQUserInfO;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private ILocalAuthService localAuthService;

    @Resource
    private IThirdOauthService thirdOauthService;

    @Resource
    private TokenService tokenService;

    @IgnoreAuth
    @GetMapping("/qqUrl")
    public Result<String> qqUrl() {
        return Result.ok(thirdOauthService.qqUrl());
    }

    @IgnoreAuth
    @GetMapping("/qqLogin")
    public Result<String> qqLogin(@RequestParam String code, @RequestParam String state) {
        UserInfoVO userInfoVO = thirdOauthService.qqLogin(code, state);
        return Result.ok(tokenService.createToken(userInfoVO));
    }

    @IgnoreAuth
    @PostMapping("/login")
    @Deprecated
    public Result<String> login(@RequestBody @Valid LoginRO loginRO) {
        User loginUser = localAuthService.login(loginRO);
        return Result.ok();
    }

    @IgnoreAuth
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody @Valid RegisterRO registerRO) {
        return Result.ok(localAuthService.register(registerRO));
    }

    @GetMapping("/logout")
    public Result<Boolean> logout() {
        return Result.ok(localAuthService.logout());
    }

}
