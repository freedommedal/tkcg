package com.sgota.tkcg;

/**
 * @author tiankuo
 */
public abstract class TkcgLog {

    protected static TkcgLog log;

    public static void info(String message) {
        log.internalInfo(message);
    }

    public static void error(String message) {
        log.internalError(message);
    }

    public abstract void internalInfo(String message);

    public abstract void internalError(String message);
}
