package com.ruoqing.dynastyForum.qo;

import com.ruoqing.dynastyForum.common.PageQO;
import lombok.Data;

@Data
public class PostQO extends PageQO {

    private Integer categoryId;

    private String postTitle;

}
