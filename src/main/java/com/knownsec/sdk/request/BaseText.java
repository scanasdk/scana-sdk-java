package com.knownsec.sdk.request;

import com.knownsec.sdk.response.BaseResponse;
import com.knownsec.sdk.validation.limitation.NotBlank;
import com.knownsec.sdk.validation.limitation.Size;

/**
 * 文本检测请求基类
 */
public class BaseText<T extends BaseResponse> extends PostJsonRequest<T> {
    /**
     * 文本唯一内容ID
     */
    @NotBlank(message = "contentId不能为空")
    private String contentId;
    /**
     * 文本内容
     */
    @NotBlank(message = "data不能为空")
    @Size(max = 10000, message = "文本内容最长10000个字符")
    private String data;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }


    public String contentId() {
        return contentId;
    }

    public String getData() {
        return data;
    }

    public String data() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Text{" +
                "contentId='" + contentId + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public Class<T> getResponseClass() {
        return null;
    }
}
