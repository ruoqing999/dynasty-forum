package com.ruoqing.dynastyForum.ro;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterRO extends LoginRO{

    private String nickName;

}
