package com.ruoqing.dynastyForum.common;


import com.ruoqing.dynastyForum.handler.ServiceException;

public class Assert {

    public static void isTrue(boolean flag, String msg) {
        if (flag) {
            throw new ServiceException(msg);
        }
    }

}
