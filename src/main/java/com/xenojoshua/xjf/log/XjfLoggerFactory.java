package com.xenojoshua.xjf.log;

import org.apache.log4j.Logger;

public class XjfLoggerFactory {
    private static final Logger logger = Logger.getLogger(XjfLoggerFactory.class.getName());

    /**
     * Get instance of org.apache.log4j.Logger
     * @return Logger logger
     */
    public static Logger get() {
        return XjfLoggerFactory.logger;
    }
}
