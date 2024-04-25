package com.ruoqing.dynastyForum.api;

import com.ruoqing.dynastyForum.vo.QQAccessToken;
import com.ruoqing.dynastyForum.vo.QQUserInfO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "qq-oauth", url = "https://graph.qq.com")
public interface QQApi {

    @GetMapping("/oauth2.0/token")
    String getAccessToken(@RequestParam("grant_type") String grantType, @RequestParam("client_id") String appId,
                                 @RequestParam("client_secret") String appKey, @RequestParam("code") String code,
                                 @RequestParam("redirect_uri") String redirectUri, @RequestParam("fmt") String fmt,
                                 @RequestParam("need_openid") Integer needOpenid);

    @GetMapping("/user/get_user_info")
    String getUserInfo(@RequestParam("access_token") String accessToken, @RequestParam("oauth_consumer_key") String appId,
                           @RequestParam("openid") String openId);

}
