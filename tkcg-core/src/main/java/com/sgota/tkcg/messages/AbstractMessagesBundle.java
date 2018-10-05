package com.sgota.tkcg.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化信息工具
 *
 * @author tiankuo
 */
public abstract class AbstractMessagesBundle {

    /**
     * 资源包基础名
     */
    private String bundleName;

    /**
     * 构造方法.
     *
     * @param bundleName the bundleName
     */
    public AbstractMessagesBundle(String bundleName){
        this.bundleName = bundleName;
    }

    /**
     * 获取国际化信息
     *
     * @param code 信息代码
     * @param args 格式化参数列表
     * @return the message
     */
    public String getMessage(String code, Object... args) {
        return getMessage(null, code, args);
    }

    /**
     * 获取国际化信息
     *
     * @param locale the locale
     * @param code 信息
     * @param args 格式化参数列表
     * @return the message
     */
    public String getMessage(Locale locale, String code, Object... args) {
        if (code == null) {
            return null;
        }
        String message = null;
        ResourceBundle bundle;
        if (locale == null) {
            bundle = ResourceBundle.getBundle(bundleName);
        } else {
            bundle = ResourceBundle.getBundle(bundleName, locale);
        }
        if (bundle != null) {
            message = bundle.getString(code);
        }
        if (message != null && args != null) {
            message = MessageFormat.format(message, args);
        }
        return message;
    }
}
