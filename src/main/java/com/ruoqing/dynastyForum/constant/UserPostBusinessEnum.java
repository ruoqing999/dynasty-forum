package com.ruoqing.dynastyForum.constant;

import lombok.Getter;

@Getter
public enum UserPostBusinessEnum {

    COLLECT(1, "收藏"),
    APPROVE(2, "点赞"),
    COMMENT(3, "评论"),
    REPLY(4, "回复"),
    DELETE(6, "删除"),
    EDIT(7, "编辑"),
    ADD(8, "添加"),
    VIEW(9, "浏览"),
    DOWNLOAD(10, "下载"),
    UPLOAD(11, "上传"),
    SHARE(12, "分享"),
    RECOMMEND(13, "推荐"),
    ;

    private final Integer code;
    private final String desc;

    UserPostBusinessEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}

