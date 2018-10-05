package com.sgota.tkcg;

/**
 * 生成器异常
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
}
