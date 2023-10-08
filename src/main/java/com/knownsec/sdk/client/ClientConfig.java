package com.knownsec.sdk.client;

import com.knownsec.sdk.auth.Credentials;
import com.knownsec.sdk.auth.Signer;
import com.knownsec.sdk.auth.SignerImpl;
import com.knownsec.sdk.http.HttpClientConfig;
import com.knownsec.sdk.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ClientConfig {
    public static final String DEFAULT_MODERATION_DOMAIN = "newkmsapi.qixincha.com"; // 默认调用地址
    public static final int DEFAULT_MAX_RETRY_COUNT = 3;

    public static final Signer DEFAULT_SIGNER = SignerImpl.INSTANCE;

    public static final int MAX_RETRY_COUNT_LIMIT = 10;

    public static final int NO_RETRY_COUNT = 0;
    private Credentials credentials;

    private Signer signer = DEFAULT_SIGNER;

    public Signer getSigner() {
        return signer;
    }

    public void setSigner(Signer signer) {
        this.signer = signer;
    }

    public Signer signer() {
        return signer;
    }

    private HttpClientConfig httpClientConfig;
    private String moderationDomain = DEFAULT_MODERATION_DOMAIN;
    private int maxRetryCount = DEFAULT_MAX_RETRY_COUNT;

    private List<Class<?>> preheatRequestClassesForValidation = new ArrayList<>();
    private List<BaseRequest> preheatRequestsForValidation = new ArrayList<>();

    public List<Class<?>> getPreheatRequestClassesForValidation() {
        return preheatRequestClassesForValidation;
    }

    public void setPreheatRequestClassesForValidation(List<Class<?>> preheatRequestClassesForValidation) {
        this.preheatRequestClassesForValidation = preheatRequestClassesForValidation;
    }

    public List<Class<?>> preheatRequestClassesForValidation() {
        return preheatRequestClassesForValidation;
    }

    public List<BaseRequest> preheatRequestsForValidation() {
        return preheatRequestsForValidation;
    }

    /**
     * 设置需要参与 javax validation 预热的 Request 类型
     */
    public ClientConfig preheatRequestClassesForValidation(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            preheatRequestClassesForValidation.add(clazz);
        }

        return this;
    }

    public void setModerationDomain(String moderationDomain) {
        this.moderationDomain = moderationDomain;
    }

    public String getModerationDomain() {
        return moderationDomain;
    }

    public void setHttpClientConfig(HttpClientConfig httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
    }

    public HttpClientConfig getHttpClientConfig() {
        return httpClientConfig;
    }

    public ClientConfig httpClientConfig(HttpClientConfig httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
        return this;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = Math.min(maxRetryCount, MAX_RETRY_COUNT_LIMIT);
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public ClientConfig credentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public static ClientConfig defaultConfig(Credentials credentials) {
        return new ClientConfig().credentials(credentials).httpClientConfig(HttpClientConfig.defaultConfig());
    }
}
