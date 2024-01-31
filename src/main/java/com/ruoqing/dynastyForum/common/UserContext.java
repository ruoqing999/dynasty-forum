package com.ruoqing.dynastyForum.common;

import com.ruoqing.dynastyForum.entity.User;

public class UserContext {

    private static final ThreadLocal<User> USER_LOCAL = new ThreadLocal<>();

    public static void set(User user){
        USER_LOCAL.set(user);
    }

    public static User get(){
        return USER_LOCAL.get();
    }

    public static void remove(){
        USER_LOCAL.remove();
    }

}
