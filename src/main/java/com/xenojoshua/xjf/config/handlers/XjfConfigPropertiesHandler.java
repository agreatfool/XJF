package com.xenojoshua.xjf.config.handlers;

import com.xenojoshua.xjf.config.interfaces.XjfConfigHandlerInterface;
import net.sf.json.JSONObject;

import java.util.Properties;

public class XjfConfigPropertiesHandler implements XjfConfigHandlerInterface {

    private Properties resources;

    public XjfConfigPropertiesHandler(Properties resources) {
        this.resources = resources;
    }

    /**
     * Check key exists in resources or not.
     * @param key
     * @return exists
     */
    @Override
    public boolean hasKey(String key) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Load string resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public String loadString(String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Load json resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public JSONObject loadJson(String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Load int resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public int loadInt(String key) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Load float resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public float loadFloat(String key) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
