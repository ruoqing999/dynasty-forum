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
 * @since 2024-04-09
 */
@Getter
@Setter
@TableName("dy_notice")
public class Notice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "notice_id", type = IdType.ASSIGN_ID)
    private Integer noticeId;

    @TableField("notice_title")
    private String noticeTitle;

    @TableField("notice_content")
    private String noticeContent;
}
