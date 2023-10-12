package com.newkms.qixincha.moderation.image.v3.async;

import com.newkms.qixincha.request.BaseImage;
import com.newkms.qixincha.request.BizPostJsonRequest;
import com.newkms.qixincha.validation.limitation.NotBlank;
import com.newkms.qixincha.validation.limitation.Size;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * v3 没有签名，签名信息写在Body里
 */
public class ImageAsyncModerationInput extends BizPostJsonRequest<ImageAsyncModerationOutput> {

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
    @Size(min = 1, max = 16, message = "1-16条文本数据")
    private List<BaseImage> images;


    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public List<BaseImage> getImages() {
        return images;
    }


    public List<BaseImage> images() {
        return images;
    }

    public void setImages(List<BaseImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ImageAsyncModerationInput{" +
                "extra='" + extra + '\'' +
                ", appId='" + appId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", images=" + images +
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
        params.put("images", this.images);
        return params;
    }


    /**
     * 同步调用接口
     */
    public ImageAsyncModerationInput(String businessId) {
        this.businessId = businessId;
        // 版本号
        version = "v3";
        // 接口地址
        uriPattern = "/kms-open/v3/image/async";
    }

    @Override
    public Class<ImageAsyncModerationOutput> getResponseClass() {
        return ImageAsyncModerationOutput.class;
    }


}
