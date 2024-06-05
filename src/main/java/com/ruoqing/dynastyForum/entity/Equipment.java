package com.ruoqing.dynastyForum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoqing.dynastyForum.common.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author java
 * @since 2024-06-05
 */
@Getter
@Setter
@TableName("dy_equipment")
public class Equipment extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "equipment_id", type = IdType.AUTO)
    private Integer equipmentId;

    @TableField("equipment_name")
    private String equipmentName;

    @TableField("img")
    private String img;
}
