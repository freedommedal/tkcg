package ${basePackage}.common.exception;

import java.util.Locale;

import ${basePackage}.common.util.MessagesBundle;

/**
 * Biz异常
 *
 * @author ${author}
 */
public class BizException extends RuntimeException {

    /**
     * The constant serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 错误代码
     */
    private final String code;

    /**
     * constructor
     *
     * @param code code
     * @param args args
     */
    public BizException(String code, Object... args){
        this(null, code, args);
    }

    /**
     * constructor
     *
     * @param code code
     * @param args args
     */
    public BizException(Locale locale, String code, Object... args){
        super(MessagesBundle.message(locale, code, args));
        this.code = code;
    }

    /**
     * Gets code
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
