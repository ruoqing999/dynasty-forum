package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Post;
import lombok.Data;

@Data
public class PostVO extends Post {

    private String nickName;
    private String categoryName;
    private Integer views;
    private Integer comments;
    private Integer approves;
    private String avatar;

}
