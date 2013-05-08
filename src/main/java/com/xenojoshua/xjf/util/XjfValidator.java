package com.xenojoshua.xjf.util;

import org.apache.commons.lang.math.NumberUtils;

public class XjfValidator {

    /**
     * Validate given string is a valid ip address or not.
     * @param ip
     * @return valid
     */
    public static boolean isIP(String ip) {
        return ip.matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    }

    /**
     * Validate given int is a valid port number or not.
     * @param port
     * @return valid
     */
    public static boolean isPort(int port) {
        boolean valid = false;
        if (port > 1 && port < 65535) {
            valid = true;
        }
        return valid;
    }

    /**
     * Validate given string is a valid numeric string or not.
     * @param number
     * @return valid
     */
    public static boolean isNumeric(String number) {
        return NumberUtils.isNumber(number);
    }

}
