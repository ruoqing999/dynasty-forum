package com.ruoqing.dynastyForum.vo;

import lombok.Data;

@Data
public class QQUserInfO {

    private Integer ret;
    private String msg;
    private String nickName;

    /**
     * 大小为40×40像素的QQ头像URL
     */
    private String figureurl_qq_1;
}
