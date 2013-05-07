package com.xenojoshua.xjf.log;

import com.xenojoshua.xjf.constant.XjfConst;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class XjfLogger {

    private static Logger instance = null;

    /**
     * Initialize log4j logger.
     */
    public static void init() {
        String log4jConfPath = XjfConst.XJF_CONF_ROOT + "/log4j.properties";
        File log4jConf = new File(log4jConfPath);
        if (!log4jConf.exists() || !log4jConf.isFile()) {
            System.out.println("[Xjf System] Log4j config path invalid: " + log4jConfPath);
            System.exit(1);
        } else {
            PropertyConfigurator.configureAndWatch(log4jConfPath, XjfConst.XJF_LOGGER_CONF_RELOAD_INTERVAL);
            XjfLogger.instance = Logger.getLogger(XjfConst.XJF_LOGGER_NAME);
            System.out.println("[Xjf System] Log4j initialized with config: " + log4jConfPath);
            log4jConf = null;
        }
    }

    /**
     * Get instance of org.apache.log4j.Logger
     * @return instance
     */
    public static Logger get() {
        return XjfLogger.instance;
    }

}
