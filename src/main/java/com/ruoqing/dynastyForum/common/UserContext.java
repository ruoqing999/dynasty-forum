package com.ruoqing.dynastyForum.common;

import com.ruoqing.dynastyForum.vo.UserInfoVO;

public class UserContext {

    private static final ThreadLocal<UserInfoVO> USER_LOCAL = new ThreadLocal<>();

    public static void set(UserInfoVO user){
        USER_LOCAL.set(user);
    }

    public static UserInfoVO get(){
        return USER_LOCAL.get();
    }

    public static void remove(){
        USER_LOCAL.remove();
    }

}
