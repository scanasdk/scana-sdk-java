package com.newkms.qixincha.http;

// 枚举协议类型
public enum ProtocolEnum {
    HTTP("http"),

    HTTPS("https");

    private final String protocol;

    ProtocolEnum(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return protocol;
    }
}
