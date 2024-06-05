package com.ruoqing.dynastyForum.qo;

import com.ruoqing.dynastyForum.common.PageQO;
import lombok.Data;

import java.util.List;

@Data
public class PostQO extends PageQO {

    private List<Integer> categoryIds;

    private String postTitle;

    private List<Integer> postIds;

    private Integer userId;

    private Integer isSelf;
}
