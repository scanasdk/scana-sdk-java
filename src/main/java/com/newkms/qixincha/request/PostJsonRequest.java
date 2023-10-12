

package com.newkms.qixincha.request;


import com.google.gson.Gson;
import com.newkms.qixincha.auth.Credentials;
import com.newkms.qixincha.auth.SignResult;
import com.newkms.qixincha.auth.Signer;
import com.newkms.qixincha.http.HttpHeaders;
import com.newkms.qixincha.http.HttpMethodEnum;
import com.newkms.qixincha.http.HttpRequest;
import com.newkms.qixincha.response.BaseResponse;
import com.newkms.qixincha.utils.StringHashMap;

import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * 为方便子服务对接，集成常用操作(带有签名版本)
 *
 * @formatter:on
 */
public abstract class PostJsonRequest<T extends BaseResponse> extends BaseRequest<T> {

    protected String version;
    protected long timestamp = System.currentTimeMillis();
    protected String nonce = UUID.randomUUID().toString().replace("-", "");


    private static final Gson GSON = new Gson();

    protected PostJsonRequest() {
        method = HttpMethodEnum.POST;
    }

    public String getVersion() {
        return version;
    }

    public String version() {
        return version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long timestamp() {
        return timestamp;
    }

    public PostJsonRequest<T> timestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String nonce() {
        return nonce;
    }

    public PostJsonRequest<T> nonce(String nonce) {
        this.nonce = nonce;
        return this;
    }


    /**
     * 获取待签名参数列表，调用子类方法添加个性化参数。<br/>
     * 自动添加公共参数包括 version, timestamp, nonce, signatureMethod（如果不为null）。<br/>
     * 注：appId 会在计算签名前被自动添加。
     *
     * @return 返回需要参与签名的参数
     */
    public Map<String, String> getSignParams() {
        Map<String, String> params = getCustomSignParams();
        params.put("version", this.version);
        params.put("timestamp", String.valueOf(this.timestamp));
        params.put("nonce", this.nonce);
        return params;
    }

    /**
     * 获取具体业务中特有的需要参与签名计算的参数
     *
     * @return 返回需要参与签名的参数
     */
    protected Map<String, String> getCustomSignParams() {
        return new StringHashMap();
    }

    /**
     * 获取具体业务中特有的不参与签名计算的参数
     *
     * @return 返回无需参与签名的参数
     */
    protected Map<String, Object> getNonSignParams() {
        return Collections.emptyMap();
    }


    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = super.getHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
        headers.put(HttpHeaders.SDK_VERSION, HttpHeaders.DEFAULT_SDK_VERSION);
        return headers;
    }

    /**
     * 构建body，将参数以form表单格式组装放入body中
     *
     * @param signer
     * @param credentials
     * @return
     */
    private byte[] getBodyWithSign(Signer signer, Credentials credentials) {
        Map<String, Object> params = new HashMap<>();
        if (signer != null) {
            Map<String, String> signMap = getSignParams();
            // 是否需要签名
            SignResult signResult = signer.genSignature(credentials, signMap);
            params.put("appId", signResult.getAppId());
            params.put("signature", signResult.getSignature());
        }
        params.putAll(getNonSignParams());
        return GSON.toJson(params).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 构建http请求
     *
     * @param credentials
     * @return
     */
    @Override
    public HttpRequest toHttpRequest(Signer signer, Credentials credentials) {
        return new HttpRequest()
                .headers(getHeaders())
                .protocol(protocol)
                .method(method)
                .domain(moderationDomain)
                .uriPattern(uriPattern)
                .body(this.getBodyWithSign(signer, credentials));
    }

    @Override
    public String toString() {
        String str = "PostFormRequest(" + "super=" + super.toString() + ", version=" + version + ", timestamp=" + timestamp + ", nonce=" + nonce + ")";
        return str;
    }
}
