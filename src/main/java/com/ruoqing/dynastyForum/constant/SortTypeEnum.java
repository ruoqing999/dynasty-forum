package com.ruoqing.dynastyForum.constant;

import lombok.Getter;

@Getter
public enum SortTypeEnum {

    DEFAULT(0, "默认排序"),

    HOTTEST(1, "最热排序"),

    NEWEST(2, "最新排序"),
    ;

    private final String type;
    private final int code;

    SortTypeEnum(int code, String type) {
        this.type = type;
        this.code = code;
    }

}
