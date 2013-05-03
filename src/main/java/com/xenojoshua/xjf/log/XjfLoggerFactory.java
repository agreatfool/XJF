package com.xenojoshua.xjf.log;

import com.xenojoshua.xjf.constant.XjfConst;
import org.apache.log4j.Logger;

public class XjfLoggerFactory {
    private static Logger logger = null;

    public static void init(String configPath) {
        XjfLoggerFactory.logger = Logger.getLogger(XjfConst.XJF_LOGGER_NAME);
    }

    /**
     * Get instance of org.apache.log4j.Logger
     * @return Logger logger
     */
    public static Logger get() throws Exception {
        if (XjfLoggerFactory.logger == null) {
            throw new Exception("[Xjf System] XjfLoggerFactory not initialized");
        }
        return XjfLoggerFactory.logger;
    }
}
