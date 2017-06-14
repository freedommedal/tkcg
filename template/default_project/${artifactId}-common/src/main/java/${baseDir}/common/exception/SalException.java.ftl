package ${basePackage}.common.exception;

/**
 * Sal异常
 *
 * @author ${author}
 */
public class SalException extends RuntimeException {

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
     * @param msg msg
     */
    public SalException(String code, String msg){
        super(msg);
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
