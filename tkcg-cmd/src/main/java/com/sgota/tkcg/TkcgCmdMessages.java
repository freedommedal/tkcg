package com.sgota.tkcg;

import com.sgota.tkcg.messages.AbstractMessagesBundle;

/**
 * I18N
 *
 * @author tiankuo
 */
public class TkcgCmdMessages extends AbstractMessagesBundle {

    /**
     * The constant BUNDLE_NAME
     */
    public static final String BUNDLE_NAME = "messages.tkcg_cmd";
    /**
     * The constant INSTANCE
     */
    private static final TkcgCmdMessages INSTANCE = new TkcgCmdMessages();

    /**
     * 构造方法
     */
    private TkcgCmdMessages(){
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
