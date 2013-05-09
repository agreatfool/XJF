package com.xenojoshua.xjf.util;

import com.xenojoshua.xjf.log.XjfLogger;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class XjfUtil {

    /**
     * Calculate the md5 value of the given int.
     * @param origin
     * @return digest
     */
    public static String md5(int origin) {
        return XjfUtil.md5(String.valueOf(origin));
    }

    /**
     * Calculate the md5 value of the given double.
     * @param origin
     * @return digest
     */
    public static String md5(double origin) {
        return XjfUtil.md5(String.valueOf(origin));
    }

    /**
     * Calculate the md5 value of the given string.
     * @param origin
     * @return digest
     */
    public static String md5(String origin) {
        return DigestUtils.md5Hex(origin);
    }

}
