package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Post;
import lombok.Data;

@Data
public class PostDetailVO extends Post {

    private String avatar;

    private String nickName;
}
