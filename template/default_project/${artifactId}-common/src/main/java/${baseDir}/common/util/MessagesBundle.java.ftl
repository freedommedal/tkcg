package ${basePackage}.common.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化信息工具
 *
 * @author ${author}
 */
public class MessagesBundle {

    /**
     * 资源包基础名
     */
    private static final String BUNDLE_NAME = "messages.error";

    /**
     * constructor
     */
    private MessagesBundle(){
        // private
    }

    /**
     * 获取国际化信息
     *
     * @param code 信息代码
     * @param args 格式化参数列表
     * @return message
     */
    public static String message(String code, Object... args) {
        return message(null, code, args);
    }

    /**
     * 获取国际化信息
     *
     * @param locale the locale
     * @param code 信息
     * @param args 格式化参数列表
     * @return message
     */
    public static String message(Locale locale, String code, Object... args) {
        if (code == null) {
            return null;
        }
        String message = null;
        ResourceBundle bundle;
        if (locale == null) {
            bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        } else {
            bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
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
