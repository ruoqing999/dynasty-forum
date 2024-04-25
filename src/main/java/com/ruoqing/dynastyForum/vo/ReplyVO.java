package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Reply;
import lombok.Data;

@Data
public class ReplyVO extends Reply {

    private String avatar;
    private String nickName;
    private String replyNickName;

}
