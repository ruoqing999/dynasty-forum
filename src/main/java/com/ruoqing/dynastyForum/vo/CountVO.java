package com.ruoqing.dynastyForum.vo;

import lombok.Data;

@Data
public class CountVO {

    private int postCount;
    private int userCount;
    // 整个系统所有的评论次数
    private int commentCount;
    // 单篇帖子的评论次数
    private int comments;
    private int collections;
    private int views;
    private int approves;
    private boolean isLike;
    private boolean isFollow;
    private boolean isCollect;
}
