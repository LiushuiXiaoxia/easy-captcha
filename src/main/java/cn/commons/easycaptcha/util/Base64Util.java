package cn.commons.easycaptcha.util;

import java.util.Base64;

public class Base64Util {

    public static String base64(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes));
    }
}