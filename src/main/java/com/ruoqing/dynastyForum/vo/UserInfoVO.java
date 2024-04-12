package com.ruoqing.dynastyForum.vo;

import lombok.Data;

@Data
public class UserInfoVO {

    private String userKey;

    private String nickName;

    private String avatarUrl;

    private String openId;

    private Integer oauthType;

}
