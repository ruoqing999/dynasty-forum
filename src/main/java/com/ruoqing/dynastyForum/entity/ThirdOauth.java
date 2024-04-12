package com.ruoqing.dynastyForum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoqing.dynastyForum.common.BaseEntity;
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
@TableName("dy_third_oauth")
public class ThirdOauth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "third_oauth_id", type = IdType.AUTO)
    private Integer thirdOauthId;

    @TableField("oauth_type")
    private Integer oauthType;

    @TableField("oauth_id")
    private String oauthId;
}
