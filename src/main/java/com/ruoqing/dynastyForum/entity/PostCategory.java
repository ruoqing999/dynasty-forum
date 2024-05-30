package com.ruoqing.dynastyForum.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoqing.dynastyForum.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("dy_post_category")
public class PostCategory extends BaseEntity {

    @TableId(value = "post_category_id", type = IdType.AUTO)
    private Integer postCategoryId;

    @TableField("post_id")
    private Integer postId;

    @TableField("category_id")
    private Integer categoryId;

}
