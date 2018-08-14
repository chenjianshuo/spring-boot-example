package com.cjs.crawler.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Created by chenjianshuo on 2018/8/14 11:02.
 */
public class MD5Utils {

    /**
     * 生成标准的32位MD5值, null、空字符串、多个空格字符串均返回空字符
     * @param text
     * @return
     */
    public static String md5(String text) {
        if (StringUtils.isBlank(text)){
            return "";
        }

        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes(StandardCharsets.UTF_8));
            for (byte b : md.digest()) {
                int n = b;
                if(n < 0) n += 256;
                if(n < 16) sb.append("0");
                sb.append(Integer.toHexString(n));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
