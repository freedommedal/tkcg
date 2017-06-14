package ${basePackage}.common.error;

/**
 * 错误码 基础
 *
 * @author ${author}
 */
public enum ErrorBase {
    /**
     * System error
     */
    SYSTEM_ERROR("SYSTEM_ERROR"),
    /**
     * Params illegal
     */
    PARAMS_ILLEGAL("PARAMS_ILLEGAL"),
    /**
     * Params not null
     */
    PARAMS_NOT_NULL("PARAMS_NOT_NULL"),
    /**
     * Params not blank
     */
    PARAMS_NOT_BLANK("PARAMS_NOT_BLANK"),
    /**
     * Params not gt
     */
    PARAMS_NOT_GT("PARAMS_NOT_GT"),
    /**
     * Params not lt
     */
    PARAMS_NOT_LT("PARAMS_NOT_LT"),
    /**
     * Params not equal
     */
    PARAMS_NOT_EQUAL("PARAMS_NOT_EQUAL"),
    /**
     * Params not true
     */
    PARAMS_NOT_TRUE("PARAMS_NOT_TRUE"),
    /**
     * Params not false
     */
    PARAMS_NOT_FALSE("PARAMS_NOT_FALSE"),
    /**
     * Success
     */
    SYSTEM_SUCCESS("0");

    /**
     * The Code
     */
    private String code;

    /**
     * constructor
     *
     * @param code the code
     */
    ErrorBase(String code){
        this.code = code;
    }

    /**
     * code
     *
     * @return the code
     */
    public String code() {
        return code;
    }
}
