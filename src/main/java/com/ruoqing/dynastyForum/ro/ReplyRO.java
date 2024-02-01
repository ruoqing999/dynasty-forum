package com.ruoqing.dynastyForum.ro;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReplyRO {

    private Integer replyId;

    @NotNull(message = "请选择评论")
    private Integer commentId;

    @NotEmpty(message = "回复内容不能为空")
    private String content;

    private Integer replyUserId;

}
