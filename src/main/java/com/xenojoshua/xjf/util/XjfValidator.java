package com.xenojoshua.xjf.util;

import java.util.regex.Pattern;

public class XjfValidator {

    public static boolean isIP(String ip) {
        return Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$").matcher(ip).matches();
    }

}
