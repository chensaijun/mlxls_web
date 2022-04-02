package com.melot.testng.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author biaoge
 * @version 1.0.0
 * @date 2019/04/21
 */
public class PasswordFunctions {
    /**
     * @param password
     * @return 加密后的密码
     * @throws Exception
     */
    public static String encodePassword(String password)
            throws Exception {
        //密码加密
        DigestUtils digestUtils = new DigestUtils();
        String encodeString = "rCt52pF2cnnKNB3Hkp";
        String encodePassword = String.valueOf(digestUtils.md5Hex(encodeString + password));
        return encodePassword;
    }
}
