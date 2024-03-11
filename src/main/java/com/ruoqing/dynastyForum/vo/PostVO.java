package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Post;
import lombok.Data;

@Data
public class PostVO extends Post {

    private String nickName;
    private String categoryName;
    private Integer views = 1000;
    private Integer comments = 200;

    private String avatar = "https://img.ioc.ceramicclouds.com/statics/2023/10/17/blob_20231017165724A006.png";

}
