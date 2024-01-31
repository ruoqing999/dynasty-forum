package com.ruoqing.dynastyForum.entity;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoqing.dynastyForum.common.BaseEntity;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dy_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Integer userId;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
}
