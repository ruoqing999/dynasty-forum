package com.ruoqing.dynastyForum.controller;


import com.ruoqing.dynastyForum.annotation.IgnoreAuth;
import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.component.TokenService;
import com.ruoqing.dynastyForum.entity.User;
import com.ruoqing.dynastyForum.ro.LoginRO;
import com.ruoqing.dynastyForum.ro.RegisterRO;
import com.ruoqing.dynastyForum.service.ILocalAuthService;
import com.ruoqing.dynastyForum.service.IThirdOauthService;
import com.ruoqing.dynastyForum.vo.UserInfoVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public void qqLogin(@RequestParam String code, @RequestParam String state, HttpServletResponse response) throws IOException {
        String host = "https://wcby.ruoqing.club";
        UserInfoVO userInfoVO = thirdOauthService.qqLogin(code, state);
        String token = tokenService.createToken(userInfoVO);
        String script = String.format("""
                <script>
                window.opener.postMessage('%s', '%s')
                window.close()
                </script>
                """, token, host);
        response.getWriter().print(script);
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

    @GetMapping("/info")
    public Result<UserInfoVO> info() {
        return Result.ok(localAuthService.getInfo());
    }

}
