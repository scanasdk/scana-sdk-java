package com.newkms.qixincha.moderation.audio.v3;

import com.newkms.qixincha.response.ApiResult;

public class AudioModerationOutput extends ApiResult {
    /**
     * 审核类型枚举
     */
    private Integer moderationType;
    /**
     * 当次审核请求id
     */
    private String requestId;
    /**
     * 客户透传字段，ScanA不会对该字段做任何处理，随结果返回给用户
     */
    private String extra;

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
        return "AudioModerationOutput{" +
                "moderationType=" + moderationType +
                ", requestId='" + requestId + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
