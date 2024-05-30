package com.ruoqing.dynastyForum.vo;

import lombok.Data;

@Data
public class UserInfoVO {

    private Integer userId;

    private String userKey;

    private String nickName;

    private String intro;

    private String avatarUrl;

    private Integer oauthType;

    private int likeCount = 299;

    private int readCount = 6533;

    private String level;


}
