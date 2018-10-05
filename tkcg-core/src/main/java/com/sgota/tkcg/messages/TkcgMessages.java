package com.sgota.tkcg.messages;

/**
 * I18N
 *
 * @author tiankuo
 */
public class TkcgMessages extends AbstractMessagesBundle {

    /**
     * The constant BUNDLE_NAME
     */
    public static final String BUNDLE_NAME = "messages.tkcg";
    /**
     * The constant INSTANCE
     */
    private static final TkcgMessages INSTANCE = new TkcgMessages();

    /**
     * 构造方法
     */
    private TkcgMessages(){
        super(BUNDLE_NAME);
    }

    /**
     * Message
     *
     * @param key key
     * @param params params
     * @return string
     */
    public static String message(String key, Object... params) {
        return INSTANCE.getMessage(key, params);
    }
}
