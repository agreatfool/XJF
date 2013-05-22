package com.xenojoshua.xjf.template;

import com.xenojoshua.xjf.constant.XjfConst;
import com.xenojoshua.xjf.log.XjfLogger;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

public class XjfTemplate {

    private static VelocityContext context;

    /**
     * Initialize template engine "Velocity".
     */
    public static void init() {
        try {
            Properties props = new Properties();
            props.setProperty(Velocity.RESOURCE_LOADER, "file"); // use file templates
            props.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            props.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, XjfConst.XJF_TPL_ROOT); // set template root
            props.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, "true");
            props.setProperty("file.resource.loader.modificationCheckInterval", "300"); // seconds
            props.setProperty(Velocity.RUNTIME_LOG, XjfConst.XJF_LOGS_ROOT + "/velocity.log"); // set log file path

            Velocity.init(props);

            XjfTemplate.reset();
        } catch (Exception e) {
            System.out.println("[Xjf System] Exception in init Velocity: " + ExceptionUtils.getStackTrace(e));
            System.exit(1);
        }
    }

    /**
     * Reset the VelocityContext. Prepare for a new template input.
     */
    public static void reset() {
        XjfTemplate.context = new VelocityContext();
    }

    /**
     * Assign data into VelocityContext.
     * @param key
     * @param value
     */
    public static void assign(String key, Object value) {
        context.put(key, value);
    }

    /**
     * Fetch the rendered template string.
     * @return
     */
    public static String fetch(String tplName) {
        Template template = null;
        try {
            template = Velocity.getTemplate(tplName);
        } catch (Exception e) {
            XjfLogger.get().error(
                String.format("[Xjf System] Error in loading template file: %s",
                    ExceptionUtils.getStackTrace(e))
            );
        }

        StringWriter writer = new StringWriter();
        try {
            template.merge(context, writer);
        } catch (IOException ioe) {
            XjfLogger.get().error(
                String.format("[Xjf System] Error in merging template writer: %s",
                    ExceptionUtils.getStackTrace(ioe))
            );
        }

        return writer.toString();
    }

}