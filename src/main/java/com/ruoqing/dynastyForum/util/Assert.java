package com.ruoqing.dynastyForum.util;


import com.ruoqing.dynastyForum.handler.exception.ServiceException;

public class Assert {

    public static void isTrue(boolean flag, String msg) {
        if (flag) {
            throw new ServiceException(msg);
        }
    }

}
