package com.xenojoshua.xjf.config.interfaces;

import net.sf.json.JSONObject;

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
     * Load float resource in config file.
     * @param key
     * @return resource
     */
    float loadFloat(String key);

    /**
     * Load json resource in config file.
     * @param key
     * @return resource
     */
    JSONObject loadJson(String key);
}
