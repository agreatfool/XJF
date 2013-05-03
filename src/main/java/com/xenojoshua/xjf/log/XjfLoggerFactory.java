package com.xenojoshua.xjf.log;

import com.xenojoshua.xjf.constant.XjfConst;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class XjfLoggerFactory {
    private static Logger logger = null;

    public static void init() {
        PropertyConfigurator.configureAndWatch(XjfConst.XJF_LOGS_ROOT + "log4j.properties");
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
