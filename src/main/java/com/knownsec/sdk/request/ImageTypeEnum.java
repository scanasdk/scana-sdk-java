package com.knownsec.sdk.request;

/**
 * 枚举图片类型
 */
public enum ImageTypeEnum {
    URL(1),

    BASE64(2);

    private final Integer type;

    ImageTypeEnum(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Enums{" + "type=" + type + '}';
    }

    public Integer toInteger() {
        return type;
    }
}
