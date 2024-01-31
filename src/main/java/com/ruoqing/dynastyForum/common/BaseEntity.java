package com.ruoqing.dynastyForum.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    private Date createTime;

    private Date updateTime;

    private Integer status;

}
