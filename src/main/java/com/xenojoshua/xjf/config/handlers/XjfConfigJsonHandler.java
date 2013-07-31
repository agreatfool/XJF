package com.xenojoshua.xjf.config.handlers;

import com.alibaba.fastjson.JSONObject;
import com.xenojoshua.xjf.config.interfaces.XjfConfigHandlerInterface;

public class XjfConfigJsonHandler implements XjfConfigHandlerInterface {

    private JSONObject resources;

    public XjfConfigJsonHandler(JSONObject resources) {
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
        return resources.getString(key);
    }

    /**
     * Load int resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public int loadInt(String key) {
        return resources.getIntValue(key);
    }

    /**
     * Load json resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public JSONObject loadJson(String key) {
        return resources.getJSONObject(key);
    }

    /**
     * Load double resource in config file.
     * @param key
     * @return resource
     */
    @Override
    public double loadFloat(String key) {
        return resources.getDouble(key);
    }
}
