package com.ruoqing.dynastyForum.vo;

import lombok.Data;

@Data
public class UserOperateCountVO {

    /**
     * 文章数量
     */
    private int postCount;

    /**
     * 点赞数量
     */
    private int approveCount;

    /**
     * 关注数量
     */
    private int followCount;

    /**
     * 粉丝数量
     */
    private int fanCount;

}
