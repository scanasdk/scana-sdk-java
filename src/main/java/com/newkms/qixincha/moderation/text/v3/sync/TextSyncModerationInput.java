package com.newkms.qixincha.moderation.text.v3.sync;

import com.newkms.qixincha.request.BaseText;
import com.newkms.qixincha.request.BizPostJsonRequest;
import com.newkms.qixincha.validation.limitation.NotBlank;
import com.newkms.qixincha.validation.limitation.Size;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * v3 没有签名，签名信息写在Body里
 */
public class TextSyncModerationInput extends BizPostJsonRequest<TextSyncModerationOutput> {
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

    /**
     * 文本请求内容
     */
    @Size(min = 1, max = 100, message = "1-100条文本数据")
    private List<BaseText> text;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public List<BaseText> getText() {
        return text;
    }


    public List<BaseText> texts() {
        return text;
    }

    public void setText(List<BaseText> text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextSyncModerationInput{" +
                "extra='" + extra + '\'' +
                ", appId='" + appId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", text=" + text +
                ", businessId='" + businessId + '\'' +
                '}';
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
        params.put("text", this.text);
        return params;
    }

    @Override
    public byte[] getBody() {
        return super.getBody();
    }

    /**
     * 同步调用接口
     */
    public TextSyncModerationInput(String businessId) {
        this.businessId = businessId;
        // 版本号
        version = "v3";
        // 接口地址
        uriPattern = "/kms-open/v3/text/sync";
    }

    @Override
    public Class<TextSyncModerationOutput> getResponseClass() {
        return TextSyncModerationOutput.class;
    }


}
