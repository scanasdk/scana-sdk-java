/*
 * @(#) AssertUtils.java 2020-07-07
 *
 * Copyright 2020 NetEase.com, Inc. All rights reserved.
 */

package com.newkms.qixincha.utils;

/**
 * 断言工具
 */
public class AssertUtils {

    private AssertUtils() {
    }

    /**
     * 断言是否是空字符串
     *
     * @param str
     * @param message
     */
    public static void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言对象是否是null
     *
     * @param obj
     * @param message
     */

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
