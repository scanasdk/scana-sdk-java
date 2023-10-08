package com.knownsec.sdk.moderation.text.v3.async;

import com.knownsec.sdk.response.ApiResult;


/**
 * 文本异步审核响应
 */
public class TextAsyncModerationOutput extends ApiResult {
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


    public TextAsyncModerationOutput() {
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
        return "TextModerationOutput{" +
                "moderationType=" + moderationType +
                ", requestId='" + requestId + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
