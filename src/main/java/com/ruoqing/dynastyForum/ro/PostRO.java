package com.ruoqing.dynastyForum.ro;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class PostRO {

    private Integer postId;

    @Length(max = 20, message = "帖子标题不能超过20个字喔")
    private String postTitle;

    private String postContent;

    private String rawContent;

    private List<Integer> categoryIds;

}
