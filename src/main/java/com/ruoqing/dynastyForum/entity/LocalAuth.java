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
@TableName("dy_local_auth")
public class LocalAuth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "local_auth_id", type = IdType.ASSIGN_ID)
    private Integer localAuthId;

    @TableField("user_id")
    private Integer userId;

    @TableField("account")
    private String account;

    @TableField("password")
    private String password;
}
