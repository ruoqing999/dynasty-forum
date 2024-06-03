package com.ruoqing.dynastyForum.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static String html2Text(String html) {
        String htmlStr = html;
        String textStr = "";

        try {
            String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regExHtml = "<[^>]+>";
            Pattern pScript = Pattern.compile(regExScript, 2);
            Matcher mScript = pScript.matcher(htmlStr);
            htmlStr = mScript.replaceAll("");
            Pattern pStyle = Pattern.compile(regExStyle, 2);
            Matcher mStyle = pStyle.matcher(htmlStr);
            htmlStr = mStyle.replaceAll("");
            Pattern pHtml = Pattern.compile(regExHtml, 2);
            Matcher mHtml = pHtml.matcher(htmlStr);
            htmlStr = mHtml.replaceAll("");
            textStr = htmlStr;
        } catch (Exception var12) {
            Exception e = var12;
            System.err.println("Html2Text: " + e.getMessage());
        }

        textStr = textStr.replaceAll("[ ]+", " ");
        textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        return textStr;
    }

}
