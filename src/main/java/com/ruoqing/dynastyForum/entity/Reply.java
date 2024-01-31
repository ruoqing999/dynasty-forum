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
@TableName("dy_reply")
public class Reply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "reply_id", type = IdType.ASSIGN_ID)
    private Integer replyId;

    @TableField("comment_id")
    private Integer commentId;

    @TableField("user_id")
    private Integer userId;

    @TableField("content")
    private String content;
}
