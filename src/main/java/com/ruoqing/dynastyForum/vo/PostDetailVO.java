package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostDetailVO extends Post {

    private String avatar;

    private String nickName;

    private String level;

    private List<CategoryVO> categoryVOS;
}
