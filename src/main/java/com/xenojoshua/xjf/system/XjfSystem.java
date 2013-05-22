package com.xenojoshua.xjf.system;

import com.xenojoshua.xjf.config.XjfConfig;
import com.xenojoshua.xjf.constant.XjfConst;
import com.xenojoshua.xjf.log.XjfLogger;
import com.xenojoshua.xjf.template.XjfTemplate;

import java.io.File;

public class XjfSystem {

    /**
     * Initialize XJF system.
     * @param jarRoot
     */
    public static void init(String jarRoot) {
        System.out.println("[Xjf System] initializing...");

        XjfSystem.initDirectories(jarRoot);
        XjfSystem.initLogger();
        XjfSystem.initConfig();
        XjfSystem.initTemplate();

        System.out.println("[Xjf System] initialized...");
    }

    /**
     * Validate system root & logs & configs directory.
     * @param jarRoot root path of the jar file
     */
    private static void initDirectories(String jarRoot) {
        // validate jar root
        XjfSystem.validateDirectory(jarRoot);
        if (jarRoot.lastIndexOf('/') == (jarRoot.length() - 1)) {
            jarRoot = jarRoot.substring(0, (jarRoot.length() - 1)); // remove tailing '/'
        }
        XjfConst.XJF_ROOT = jarRoot;
        // used by log4j
        // log4j.appender.DLD.File = ${WORKDIR}/logs/debug.log
        System.setProperty("WORKDIR", jarRoot);
        System.out.println("[Xjf System] Root path set: " + jarRoot);

        // validate logs directory
        String logsRoot = jarRoot + "/logs";
        XjfSystem.validateDirectory(logsRoot);
        XjfConst.XJF_LOGS_ROOT = logsRoot;
        System.out.println("[Xjf System] Logs path set: " + logsRoot);

        // validate config directory
        String confRoot = jarRoot + "/configs";
        XjfSystem.validateDirectory(confRoot);
        XjfConst.XJF_CONF_ROOT = confRoot;
        System.out.println("[Xjf System] Configs path set: " + confRoot);

        // validate template directory
        String tplRoot = jarRoot + "/templates";
        XjfSystem.validateDirectory(tplRoot);
        XjfConst.XJF_TPL_ROOT = tplRoot;
        System.out.println("[Xjf System] Templates path set: " + tplRoot);
    }

    /**
     * Initialize XjfLogger.
     */
    private static void initLogger() {
        XjfLogger.init();
    }

    /**
     * Initialize XjfConfig.
     */
    private static void initConfig() {
        XjfConfig.init();
    }

    /**
     * Initialize XjfTemplate.
     */
    private static void initTemplate() {
        XjfTemplate.init();
    }

    /**
     * Validate directory exists & writable.
     * @param dir
     */
    private static void validateDirectory(String dir) {
        File dirFile = new File(dir);
        if (!dirFile.exists() || !dirFile.isDirectory() || !dirFile.canWrite()) {
            System.out.println("[Xjf System] Path to be verified is invalid: " + dir);
            System.exit(1);
        }
        dirFile = null; // release resources
    }

}
