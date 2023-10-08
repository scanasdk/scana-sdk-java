package com.newkms.qixincha.moderation.text.v3.sync;

import com.newkms.qixincha.response.ApiResult;
import com.newkms.qixincha.response.TextModerationResult;

import java.util.List;

public class TextSyncModerationOutput extends ApiResult {
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

    /**
     * 审核结果
     */
    private List<TextModerationResult> moderationResult;


    public TextSyncModerationOutput() {
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

    public List<TextModerationResult> getModerationResult() {
        return moderationResult;
    }

    public void setModerationResult(List<TextModerationResult> moderationResult) {
        this.moderationResult = moderationResult;
    }

    @Override
    public String toString() {
        return "TextModerationOutput{" +
                "moderationType=" + moderationType +
                ", requestId='" + requestId + '\'' +
                ", extra='" + extra + '\'' +
                ", moderationResult=" + moderationResult +
                '}';
    }
}
