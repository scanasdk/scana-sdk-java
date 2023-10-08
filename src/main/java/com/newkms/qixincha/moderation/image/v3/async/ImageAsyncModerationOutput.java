package com.newkms.qixincha.moderation.image.v3.async;

import com.newkms.qixincha.response.ApiResult;

public class ImageAsyncModerationOutput extends ApiResult {
    /**
     * 审核类型枚举
     */
    private Integer moderationType;
    /**
     * 当次审核请求id
     */
    private String requestId;
    /**
     * 透传字段
     */
    private String extra;


    public ImageAsyncModerationOutput() {
    }

    public Integer getModerationType() {
        return moderationType;
    }

    public void setModerationType(Integer moderationType) {
        this.moderationType = moderationType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ImageAsyncModerationOutput{" +
                "moderationType=" + moderationType +
                ", requestId='" + requestId + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
