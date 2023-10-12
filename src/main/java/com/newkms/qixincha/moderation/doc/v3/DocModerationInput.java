package com.newkms.qixincha.moderation.doc.v3;

import com.newkms.qixincha.request.BizPostJsonRequest;
import com.newkms.qixincha.validation.limitation.NotBlank;
import com.newkms.qixincha.validation.limitation.Size;

import java.util.HashMap;
import java.util.Map;

public class DocModerationInput extends BizPostJsonRequest<DocModerationOutput> {
    /**
     * 额外信息，会在响应/回调中返回
     */
    private String extra;

    /**
     * appId
     */
    @NotBlank(message = "appId不能为空")
    private String appId;
    @NotBlank(message = "secretKey不能为空")
    private String secretKey;


    /**
     * 音频链接
     */
    @NotBlank(message = "url不能为空")
    private String url;

    /**
     * 唯一id
     *
     * @return
     */
    @NotBlank(message = "contentId不能为空")
    private String contentId;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Override
    protected Map<String, String> getCustomSignParams() {
        Map<String, String> params = super.getCustomSignParams();
        return params;
    }

    // v3 不需要签名
    @Override
    protected Map<String, Object> getNonSignParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("businessId", this.businessId);
        params.put("extra", this.extra);
        params.put("appId", this.appId);
        params.put("secretKey", this.secretKey);
        params.put("url", this.url);
        params.put("contentId", this.contentId);
        return params;
    }

    /**
     * 同步调用接口
     */
    public DocModerationInput(String businessId) {
        this.businessId = businessId;
        // 版本号
        version = "v3";
        // 接口地址
        uriPattern = "/kms-open/v3/doc/async";
    }

    @Override
    public Class<DocModerationOutput> getResponseClass() {
        return DocModerationOutput.class;
    }

    @Override
    public String toString() {
        return "DocModerationInput{" +
                "extra='" + extra + '\'' +
                ", appId='" + appId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", url='" + url + '\'' +
                ", contentId='" + contentId + '\'' +
                ", businessId='" + businessId + '\'' +
                '}';
    }
}
