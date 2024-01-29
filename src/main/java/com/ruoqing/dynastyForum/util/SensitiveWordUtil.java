package com.ruoqing.dynastyForum.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SensitiveWordUtil {


    // 替换符
    private static final char REPLACEMENT = '*';

    // 初始化根节点
    private static final TrieNode rootNode = new TrieNode();

    private static final String SENSITIVE_WORD_FILE = "sensitiveWord.txt";

    static {
        try (InputStream is = SensitiveWordUtil.class.getClassLoader().getResourceAsStream(SENSITIVE_WORD_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                // 添加到前缀树
                addKeyword(keyword);
                log.info("<<<<<<解析敏感词文件成功！");
            }
        } catch (IOException e) {
            log.error("<<<<<<解析敏感词文件报错！", e);
        }
    }

    public static String filter(String text) {
        if (!StringUtils.hasText(text)) {
            return "";
        }
        int begin = 0;
        int position = 0;
        TrieNode tempNode = rootNode;
        StringBuilder res = new StringBuilder();

        while (begin < text.length()) {
            if (position < text.length()) {
                char word = text.charAt(position);

                if (isSymbol(word)) {
                    if (tempNode == rootNode) {
                        begin++;
                        res.append(word);
                    }
                    position++;
                    continue;
                }
                tempNode = tempNode.getChildrenNode(word);
                if (tempNode == null) {
                    res.append(text.charAt(begin));
                    position = ++begin;
                    tempNode = rootNode;
                } else if (tempNode.isKeyWordEnd()) {
                    res.append(StringUtil.getFillString(REPLACEMENT, position - begin + 1));
                    begin = ++position;
                    tempNode = rootNode;
                } else {
                    position++;
                }
            } else {
                res.append(text.charAt(begin));
                position = ++begin;
                tempNode = rootNode;
            }
        }
        return res.toString();
    }

    // 判断是否为符号
    private static boolean isSymbol(Character c) {
        // 0x2E80~0x9FFF 是东亚文字范围
        return !CharUtil.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    private static void addKeyword(String keyword) {
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TrieNode childrenNode = tempNode.getChildrenNode(c);
            if (null == childrenNode) {
                childrenNode = new TrieNode();
                tempNode.addSubNode(c, childrenNode);
            }

            // 指向子节点,进入下一轮循环
            tempNode = childrenNode;
            // 设置结束标识
            if (i == keyword.length() - 1) {
                tempNode.setKeyWordEnd(true);
            }
        }
    }

    //前缀树
    @Data
    private static class TrieNode {
        private boolean isKeyWordEnd;
        private Map<Character, TrieNode> children = new HashMap<>();

        // 添加子节点
        public void addSubNode(Character c, TrieNode node) {
            children.put(c, node);
        }

        public TrieNode getChildrenNode(Character c) {
            return children.get(c);
        }
    }

}
