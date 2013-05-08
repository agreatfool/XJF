package com.xenojoshua.xjf.util;

import java.util.regex.Pattern;

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
     * Validate given string is a valid numeric string or not.<br/>
     * This pattern of this function matches a number with optional '-' and decimal.<br/>
     * <b>NOTE:</b><br/>
     * Be careful with the RegEx mechanism, though, as it'll fail if your using non-latin (i.e. 0 to 9) digits.<br/>
     * For example, arabic digits. This is because the "\d" part of the RegEx will only match [0-9] and effectively isn't internationally numerically aware.
     * @param number
     * @return valid
     */
    public static boolean isNumeric(String number) {
        return number.matches("-?\\d+(\\.\\d+)?");
    }

}
