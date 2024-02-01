package com.ruoqing.dynastyForum.ro;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PostRO {

    private Integer postId;

    @Length(max = 20, message = "帖子标题不能超过20个字喔")
    private String postTitle;

    private String postContent;

    private Integer categoryId;

}
