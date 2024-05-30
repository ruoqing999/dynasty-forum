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
@TableName("dy_comment")
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_id", type = IdType.ASSIGN_ID)
    private Integer commentId;

    @TableField("user_id")
    private Integer userId;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("post_id")
    private Integer postId;

    @TableField("content")
    private String content;
}
