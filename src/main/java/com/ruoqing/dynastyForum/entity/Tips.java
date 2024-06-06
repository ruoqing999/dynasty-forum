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
 * @since 2024-06-06
 */
@Getter
@Setter
@TableName("dy_tips")
public class Tips extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tips_id", type = IdType.ASSIGN_ID)
    private Integer tipsId;

    @TableField("tips_content")
    private String tipsContent;
}
