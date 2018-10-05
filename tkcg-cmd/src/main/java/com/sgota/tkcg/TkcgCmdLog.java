package com.sgota.tkcg;

import org.slf4j.Logger;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * @author tiankuo
 */
public class TkcgCmdLog extends TkcgLog {

    private static TkcgCmdLog service = new TkcgCmdLog();
    private Logger logger = StaticLoggerBinder.getSingleton().getLoggerFactory().getLogger(Logger.ROOT_LOGGER_NAME);

    /**
     * constructor
     */
    private TkcgCmdLog(){
        // private
    }

    public static void register() {
        TkcgLog.log = service;
    }

    @Override
    public void internalInfo(String message) {
        logger.info(message);
    }

    @Override
    public void internalError(String message) {
        logger.error(message);
    }
}
