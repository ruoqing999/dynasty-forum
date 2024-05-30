package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostVO extends Post {

    private String nickName;
    private String categoryName;
    private Integer comments;
    private Integer approves;
    private Integer collections;
    private String avatar;
    private String level;
    private boolean isLike;
    private List<CategoryVO> categoryVOS;
}
