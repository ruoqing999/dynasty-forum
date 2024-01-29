package com.ruoqing.dynastyForum.util;

public class StringUtil {
    public static String getFillString(char fillChar,int count){
        StringBuilder stringBuilder = new StringBuilder();
        while (count>0){
            stringBuilder.append(fillChar);
            count--;
        }
        return stringBuilder.toString();
    }
}
