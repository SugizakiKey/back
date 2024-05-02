package com.back.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;


/**
 * @author 何进业
 */
public class StringUtil {

    private static StringUtil stringUtil;

    private StringUtil() {
    }

    public static synchronized StringUtil getStringUtil() {
        if (stringUtil == null) {
            stringUtil = new StringUtil();
        }
        return stringUtil;
    }

    /**
     * 生成随机字符串
     */
    public String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * MD5加密
     */
    public String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    //生成盐值

    public String salty() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }


    public Integer changeString(String string) {
        if (string != null && !StringUtils.isBlank(string)) {
            return Integer.valueOf(string);
        } else {
            return null;
        }
    }
}
