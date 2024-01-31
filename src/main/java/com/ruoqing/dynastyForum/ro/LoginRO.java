package com.ruoqing.dynastyForum.ro;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRO {

    @NotEmpty(message = "账号不能为空")
    private String account;

    @NotEmpty(message = "密码不能为空")
    private String password;

}
