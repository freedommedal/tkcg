package com.sgota.tool.tkcg.util;

/**
 * 字符串工具类
 * 
 * @author tiankuo
 */
public class StringUtils {

    /**
     * 根据输入字符串，返回驼峰命名字符串.
     *
     * @param inputString 输入字符串
     * @param firstCharacterUppercase 首字母是否大写
     * @return 驼峰命名字符串
     */
    public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            switch (c) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;
                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }
        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }
        return sb.toString();
    }

    /**
     * 根据驼峰命名字符串，返回下划线字符串.
     *
     * @param inputString 驼峰命名字符串
     * @param separator 分隔符
     * @return 下划线字符串
     */
    public static String getSeparatorStringByCamelCase(String inputString, String separator) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(inputString.charAt(0)));
        for (int i = 1; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(separator);
            }
            sb.append(Character.toLowerCase(c));

        }
        return sb.toString();
    }
}
