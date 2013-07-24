package com.xenojoshua.xjf.config.interfaces;

import com.alibaba.fastjson.JSONObject;

public interface XjfConfigHandlerInterface {

    /**
     * Check key exists in resources or not.
     * @param key
     * @return exists
     */
    boolean hasKey(String key);

    /**
     * Load string resource in config file.
     * @param key
     * @return resource
     */
    String loadString(String key);

    /**
     * Load int resource in config file.
     * @param key
     * @return resource
     */
    int loadInt(String key);

    /**
     * Load double resource in config file.
     * @param key
     * @return resource
     */
    double loadFloat(String key);

    /**
     * Load json resource in config file.
     *
     * @param key
     * @return resource
     */
    JSONObject loadJson(String key);
}
