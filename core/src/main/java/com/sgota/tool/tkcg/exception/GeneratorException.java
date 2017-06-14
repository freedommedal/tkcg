package com.sgota.tool.tkcg.exception;

import java.text.MessageFormat;

/**
 * 自定义内部错误异常
 *
 * @author tiankuo
 */
public class GeneratorException extends RuntimeException {

    /**
     * 构造方法
     *
     * @param message 错误信息
     */
    public GeneratorException(String message){
        super(message);
    }

    /**
     * 构造方法
     *
     * @param cause the cause
     */
    public GeneratorException(Throwable cause){
        super(cause);
    }

    /**
     * 构造方法
     *
     * @param message the message
     * @param cause the cause
     */
    public GeneratorException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * 构造方法
     *
     * @param pattern 错误信息表达式
     * @param arguments 错误信息参数
     */
    public GeneratorException(String pattern, Object... arguments){
        super(MessageFormat.format(pattern, arguments));
    }
}
