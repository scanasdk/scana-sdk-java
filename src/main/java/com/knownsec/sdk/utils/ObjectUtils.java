

package com.knownsec.sdk.utils;

public class ObjectUtils {

    // 三元表达式
    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return object != null ? object : defaultValue;
    }
}
