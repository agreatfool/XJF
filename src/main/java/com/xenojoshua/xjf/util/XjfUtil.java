package com.xenojoshua.xjf.util;

import com.xenojoshua.xjf.log.XjfLogger;
import org.apache.commons.lang.exception.ExceptionUtils;

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
        String digest = null;

        try {
            digest = MessageDigest.getInstance("MD5").digest(origin.getBytes()).toString();
        } catch (NoSuchAlgorithmException nsae) {
            XjfLogger.get().error(
                String.format(
                    "[XjfUtil.md5] NoSuchAlgorithmException: %s",
                        ExceptionUtils.getStackTrace(nsae)
                )
            );
        }

        return digest;
    }

}
