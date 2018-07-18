package me.cc200.base.utils;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public abstract class StringUtil {

    public static boolean isEmpty(String str) {
        str = null == str ? "" : str.trim();
        return StringUtils.isEmpty(str);
    }

    public static boolean isInArray(String str, String[] arr) {
        return Arrays.stream(arr).anyMatch(s -> s.equals(str));
    }


}
