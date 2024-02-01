package com.ruoqing.dynastyForum.ro;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRO {

    private Integer commentId;

    @NotNull(message = "请选择帖子进行评论")
    private Integer postId;

    @NotEmpty(message = "评论内容不能为空")
    private String content;

}
