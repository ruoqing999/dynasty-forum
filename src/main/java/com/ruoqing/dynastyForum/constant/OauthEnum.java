package com.ruoqing.dynastyForum.constant;

import lombok.Getter;

@Getter
public enum OauthEnum {

    QQ("qq", 1)
    ;

    private final String type;
    private final int code;

    OauthEnum(String type, int code) {
        this.type = type;
        this.code = code;
    }

}
