package cn.commons.easycaptcha.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class Md5Util {

    public static String hash(String data) {
        return Hashing.md5().hashString(data, Charsets.UTF_8).toString();
    }
}