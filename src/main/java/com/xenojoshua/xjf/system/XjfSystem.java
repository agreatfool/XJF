package com.xenojoshua.xjf.system;

import com.xenojoshua.xjf.constant.XjfConst;

import java.io.File;

public class XjfSystem {
    /**
     * Initialize XJF system.
     * @param jarRoot
     */
    public static void init(String jarRoot) {
        System.out.println("[Xjf System] initialize...");

        XjfSystem.initDirectories(jarRoot);
    }

    /**
     * Validate system root & logs & configs directory.
     * @param jarRoot root path of the jar file
     */
    private static void initDirectories(String jarRoot) {
        // validate jar root
        File root = new File(jarRoot);
        if (!root.exists() || !root.isDirectory()) {
            System.out.println("[Xjf System] Root path is invalid: " + jarRoot);
            System.exit(1);
        } else {
            if (jarRoot.lastIndexOf('/') != jarRoot.length()) {
                jarRoot += '/';
            }
            XjfConst.XJF_ROOT = jarRoot;
            System.out.println("[Xjf System] Root path set: " + jarRoot);
        }

        // validate logs directory
        String logsRoot = jarRoot + "logs/";
        File logs = new File(logsRoot);
        if (!logs.exists() || !logs.isDirectory() || !logs.canWrite()) {
            System.out.println("[Xjf System] Logs path is invalid: " + logsRoot);
            System.exit(1);
        } else {
            XjfConst.XJF_LOGS_ROOT = logsRoot;
            System.out.println("[Xjf System] Logs path set: " + logsRoot);
        }

        // validate config directory
        String confRoot = jarRoot + "configs/";
        File confs = new File(confRoot);
        if (!confs.exists() || !confs.isDirectory() || !confs.canWrite()) {
            System.out.println("[Xjf System] Configs path is invalid: " + confRoot);
            System.exit(1);
        } else {
            XjfConst.XJF_CONF_ROOT = confRoot;
            System.out.println("[Xjf System] Configs path set: " + confRoot);
        }

        // release
        root = logs = confs = null;
    }
}
