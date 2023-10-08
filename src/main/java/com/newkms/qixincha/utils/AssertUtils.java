/*
 * @(#) AssertUtils.java 2020-07-07
 *
 * Copyright 2020 NetEase.com, Inc. All rights reserved.
 */

package com.newkms.qixincha.utils;

public class AssertUtils {

    private AssertUtils() {
    }

    public static void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
