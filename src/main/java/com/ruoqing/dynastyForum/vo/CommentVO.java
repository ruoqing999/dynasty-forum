package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Comment;
import lombok.Data;

import java.util.List;


@Data
public class CommentVO extends Comment {

    private String avatar;
    private String nickName;
    private String level;
    private int approves;
    private int repliesCount;
    private boolean isLike;
    private int depth;
    private List<CommentVO> child;

}
