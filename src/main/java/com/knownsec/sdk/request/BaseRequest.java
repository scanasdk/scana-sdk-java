package com.knownsec.sdk.request;

import com.knownsec.sdk.auth.Credentials;
import com.knownsec.sdk.auth.Signer;
import com.knownsec.sdk.http.HttpMethodEnum;
import com.knownsec.sdk.http.HttpRequest;
import com.knownsec.sdk.http.ProtocolEnum;
import com.knownsec.sdk.response.BaseResponse;
import com.knownsec.sdk.utils.StringHashMap;
import com.knownsec.sdk.validation.limitation.NotBlank;
import com.knownsec.sdk.validation.limitation.NotNull;

import java.util.Collections;
import java.util.Map;

public abstract class BaseRequest<T extends BaseResponse> {
    /**
     * 指定域名地址
     */
    protected String moderationDomain;
    /**
     * 协议类型
     */
    protected ProtocolEnum protocol;


    /**
     * URL
     */
    @NotBlank(message = "uriPattern不能为空")
    protected String uriPattern;

    public HttpMethodEnum getMethod() {
        return method;
    }

    public void setMethod(HttpMethodEnum method) {
        this.method = method;
    }

    @NotNull(message = "method不能为空")
    protected HttpMethodEnum method;

    public String getModerationDomain() {
        return moderationDomain;
    }

    public void setModerationDomain(String moderationDomain) {
        this.moderationDomain = moderationDomain;
    }

    public BaseRequest<T> moderationDomain(String moderationDomain) {
        this.moderationDomain = moderationDomain;
        return this;
    }

    public ProtocolEnum getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolEnum protocol) {
        this.protocol = protocol;
    }

    public BaseRequest<T> protocol(ProtocolEnum protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getUriPattern() {
        return uriPattern;
    }

    public void setUriPattern(String uriPattern) {
        this.uriPattern = uriPattern;
    }

    /**
     * 默认返回空，子类可继承后在其上追加
     */
    public Map<String, String> getHeaders() {
        return new StringHashMap();
    }

    /**
     * 默认返回空，子类可复写
     */
    public Map<String, String> getPathParameters() {
        return Collections.emptyMap();
    }

    /**
     * 默认返回空，子类可复写
     */
    public Map<String, String> getQueryParameters() {
        return Collections.emptyMap();
    }

    /**
     * 默认返回null，子类可复写
     */
    public byte[] getBody() {
        return null;
    }


    public abstract Class<T> getResponseClass();

    public abstract HttpRequest toHttpRequest(Signer signer, Credentials credentials);

    @Override
    public String toString() {
        return "BaseRequest{" +
                "moderationDomain='" + moderationDomain + '\'' +
                ", protocol=" + protocol +
                ", uriPattern='" + uriPattern + '\'' +
                ", method=" + method +
                '}';
    }
}
