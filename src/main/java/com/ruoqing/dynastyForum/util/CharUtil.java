package com.ruoqing.dynastyForum.util;

public class CharUtil {
    public static boolean isAsciiAlphanumeric(char ch) {
        return ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9'));
    }
}
