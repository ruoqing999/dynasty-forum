package com.ruoqing.dynastyForum.vo;


import lombok.Data;

@Data
public class QQAccessToken {

    private String access_token;
    private long expires_in;
    private String refresh_token;
    private String openid;

}
