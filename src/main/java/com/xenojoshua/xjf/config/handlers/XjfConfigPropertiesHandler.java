package com.xenojoshua.xjf.config.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xenojoshua.xjf.config.interfaces.XjfConfigHandlerInterface;

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
        return resources.containsKey(key);
    }

    /**
     * Load string resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public String loadString(String key) {
        return resources.getProperty(key);
    }

    /**
     * Load json resource in config file.
     *
     * @param key
     * @return resource
     */
    @Override
    public JSONObject loadJson(String key) {
        return JSON.parseObject(resources.getProperty(key));
    }

    /**
     * Load int resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public int loadInt(String key) {
        return Integer.parseInt(resources.getProperty(key));
    }

    /**
     * Load double resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public double loadFloat(String key) {
        return Double.parseDouble(resources.getProperty(key));
    }
}
