package com.knownsec.sdk.moderation.image.v3.sync;

import com.knownsec.sdk.response.ApiResult;
import com.knownsec.sdk.response.ImageModerationResult;

import java.util.List;

public class ImageSyncModerationOutput extends ApiResult {
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
    private List<ImageModerationResult> moderationResult;


    public ImageSyncModerationOutput() {
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

    public List<ImageModerationResult> getModerationResult() {
        return moderationResult;
    }

    public void setModerationResult(List<ImageModerationResult> moderationResult) {
        this.moderationResult = moderationResult;
    }

    @Override
    public String toString() {
        return "ImageSyncModerationOutput{" +
                "moderationType=" + moderationType +
                ", requestId='" + requestId + '\'' +
                ", extra='" + extra + '\'' +
                ", moderationResult=" + moderationResult +
                '}';
    }
}
