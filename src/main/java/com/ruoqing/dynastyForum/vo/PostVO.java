package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Post;
import lombok.Data;

@Data
public class PostVO extends Post {

    private String nickName;
    private String categoryName;
    private Integer views = 1000;
    private Integer comments = 200;
    private String avatar;

}
