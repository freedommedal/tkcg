package ${basePackage}.common.util;

import java.util.Collection;

import ${basePackage}.common.error.ErrorBase;
import ${basePackage}.common.exception.BizException;

/**
 * 参数简单检查工具
 *
 * @author ${author}
 */
public class ParamCheck {

    /**
     * The Param name
     */
    private String paramName;

    /**
     * constructor
     */
    private ParamCheck(){
        // private
    }

    /**
     * Gets paramName
     *
     * @return the paramName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Sets paramName
     *
     * @param paramName the paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * Build
     *
     * @return param simple check
     */
    public static ParamCheck build() {
        return new ParamCheck();
    }

    /**
     * name
     *
     * @param paramName paramName
     */
    public ParamCheck name(String paramName) {
        setParamName(paramName);
        return this;
    }

    public ParamCheck isNull(Object obj) {
        if (obj == null) {
            throw new BizException(ErrorBase.PARAMS_NOT_NULL.code(), paramName);
        }
        return this;
    }

    public ParamCheck isBlank(String obj) {
        if (obj == null || obj.trim().equals("")) {
            throw new BizException(ErrorBase.PARAMS_NOT_BLANK.code(), paramName);
        }
        return this;
    }

    public ParamCheck isEmpty(Collection obj) {
        if (obj == null || obj.isEmpty()) {
            throw new BizException(ErrorBase.PARAMS_NOT_BLANK.code(), paramName);
        }
        return this;
    }

    public ParamCheck lengthGe(String obj, int length) {
        if (obj.length() > length) {
            throw new BizException(ErrorBase.PARAMS_NOT_GT.code(), paramName);
        }
        return this;
    }

    public ParamCheck lengthGe(Integer obj, int length) {
        if (obj > length) {
            throw new BizException(ErrorBase.PARAMS_NOT_GT.code(), paramName);
        }
        return this;
    }

    public ParamCheck lengthLt(String obj, int length) {
        if (obj.length() < length) {
            throw new BizException(ErrorBase.PARAMS_NOT_LT.code(), paramName);
        }
        return this;
    }

    public ParamCheck lengthLt(Integer obj, int length) {
        if (obj < length) {
            throw new BizException(ErrorBase.PARAMS_NOT_LT.code(), paramName);
        }
        return this;
    }

    public ParamCheck isEqual(String obj, int length) {
        if (obj.length() == length) {
            throw new BizException(ErrorBase.PARAMS_NOT_EQUAL.code(), paramName);
        }
        return this;
    }

    public ParamCheck isEqual(Integer obj, int length) {
        if (obj == length) {
            throw new BizException(ErrorBase.PARAMS_NOT_EQUAL.code(), paramName);
        }
        return this;
    }

    public ParamCheck isTrue(Boolean obj) {
        if (obj.equals(true)) {
            throw new BizException(ErrorBase.PARAMS_NOT_TRUE.code(), paramName);
        }
        return this;
    }

    public ParamCheck isFalse(Boolean obj) {
        if (obj.equals(false)) {
            throw new BizException(ErrorBase.PARAMS_NOT_FALSE.code(), paramName);
        }
        return this;
    }

}
