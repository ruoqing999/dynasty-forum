package com.ruoqing.dynastyForum.constant;

import lombok.Getter;

@Getter
public enum OauthEnum {

    QQ("qq", 1)
    ;

    private String type;
    private int code;

    OauthEnum(String type, int code) {
        this.type = type;
        this.code = code;
    }

}
