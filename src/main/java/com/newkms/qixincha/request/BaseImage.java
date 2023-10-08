package com.newkms.qixincha.request;

import com.newkms.qixincha.response.BaseResponse;
import com.newkms.qixincha.validation.limitation.NotBlank;
import com.newkms.qixincha.validation.limitation.Size;

/**
 * 图片检测请求基类
 */
public class BaseImage<T extends BaseResponse> extends PostJsonRequest<T> {
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


    /**
     * 图片类型，1:图片链接,url  2:base64
     */
    private Integer type;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BaseImage{" +
                "contentId='" + contentId + '\'' +
                ", data='" + data + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public Class<T> getResponseClass() {
        return null;
    }
}
