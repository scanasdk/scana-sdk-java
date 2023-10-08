package com.newkms.qixincha.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObjectUtilsTest {

    @Test
    public void defaultIfNull() {
        assertEquals(Integer.valueOf(1), ObjectUtils.defaultIfNull(1, 2));
        assertEquals(Integer.valueOf(2), ObjectUtils.defaultIfNull(null, 2));
    }
}
