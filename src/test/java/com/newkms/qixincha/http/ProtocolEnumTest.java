package com.newkms.qixincha.http;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProtocolEnumTest {

    @Test
    public void testToString() {
        assertEquals("http", ProtocolEnum.HTTP.toString());
        assertEquals("https", ProtocolEnum.HTTPS.toString());
    }
}
