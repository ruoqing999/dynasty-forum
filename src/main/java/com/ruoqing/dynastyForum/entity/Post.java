package com.ruoqing.dynastyForum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoqing.dynastyForum.common.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author java
 * @since 2024-01-29
 */
@Getter
@Setter
@TableName("dy_post")
public class Post extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "post_id", type = IdType.ASSIGN_ID)
    private Integer postId;

    /**
     * 标题
     */
    @TableField("post_title")
    private String postTitle;

    /**
     * 内容
     */
    @TableField("post_content")
    private String postContent;

    @TableField("user_id")
    private Integer userId;

    @TableField("category_id")
    private Integer categoryId;
}
