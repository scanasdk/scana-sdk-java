package com.newkms.qixincha.client;

import com.google.gson.Gson;
import com.newkms.qixincha.auth.Credentials;
import com.newkms.qixincha.auth.Signer;
import com.newkms.qixincha.exception.KnownsecSdkException;
import com.newkms.qixincha.http.*;
import com.newkms.qixincha.http.HttpHeaders;
import com.newkms.qixincha.http.HttpRequest;
import com.newkms.qixincha.request.BaseRequest;
import com.newkms.qixincha.response.BaseResponse;
import com.newkms.qixincha.utils.ObjectUtils;
import com.newkms.qixincha.utils.StringUtils;
import com.newkms.qixincha.utils.ValidationUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class DefaultClient implements Client, Closeable {
    private final String defaultModerationDomain;
    private final ProtocolEnum defaultProtocol;

    private final Signer signer;

    private final Gson gson = new Gson();
    /**
     * 单个请求最多尝试次数
     */
    private final int maxAttemptCount;
    private CloseableHttpClient httpClient;
    private final Credentials credentials;

    /**
     * 获取相关证书信息
     *
     * @return
     */
    public Credentials getCredentials() {
        return credentials;
    }

    public Credentials credentials() {
        return credentials;
    }

    public DefaultClient(ClientConfig clientConfig) {
        this.credentials = clientConfig.getCredentials();
        this.signer = clientConfig.signer();
        this.defaultModerationDomain = StringUtils.defaultIfBlank(clientConfig.getModerationDomain(), ClientConfig.DEFAULT_MODERATION_DOMAIN);
        HttpClientConfig httpClientConfig = clientConfig.getHttpClientConfig();
        if (httpClientConfig == null) {
            httpClientConfig = HttpClientConfig.defaultConfig();
        }
        this.maxAttemptCount = 1 + Math.max(clientConfig.getMaxRetryCount(), 0);

        this.defaultProtocol = ObjectUtils.defaultIfNull(httpClientConfig.getProtocol(), HttpClientConfig.DEFAULT_PROTOCOL);
        this.httpClient = HttpClientFactory.create(httpClientConfig);
        this.preheatValidation(clientConfig.preheatRequestClassesForValidation());
        this.preheatValidationByInstance(clientConfig.preheatRequestsForValidation());
    }

    public String getDefaultModerationDomain() {
        return defaultModerationDomain;
    }

    public ProtocolEnum getDefaultProtocol() {
        return defaultProtocol;
    }


    public void setDefaultModerationDomain(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * 执行http request 请求
     *
     * @param request
     * @param <R>
     * @return
     * @throws KnownsecSdkException
     */
    @Override
    public <R extends BaseResponse> R execute(BaseRequest<R> request) throws KnownsecSdkException {
        // 参数校验
        ValidationUtils.validate(request);
        // 校验协议类型
        if (request.getProtocol() == null) {
            request.protocol(defaultProtocol);
        }
        // 校验域名信息
        if (StringUtils.isBlank(request.getModerationDomain())) {
            request.moderationDomain(defaultModerationDomain);
        }
        Context<R> ctx = new Context<>(request);
        KnownsecSdkException exception = null;
        while (ctx.canAttempt()) {
            // 每次重试需要关闭上一次链接
            if (ctx.response != null) {
                EntityUtils.consumeQuietly(ctx.response.getEntity());
            }
            ClassicHttpRequest httpRequest = ctx.createRequest();
            CloseableHttpResponse httpResponse = null;
            try {
                httpResponse = httpClient.execute(httpRequest);
                // 请求成功，跳出循环
                if (isServerError(httpResponse)) {
                    ctx.requestFail(httpResponse);
                } else {
                    ctx.requestSuccess(httpResponse);
                    exception = null;
                    break;
                }
            } catch (ConnectionRequestTimeoutException e) {
                // 如果请求较密集，连接池中的连接不够用，会触发此异常。这个异常不适合直接算作域名（服务）质量低
                exception = new KnownsecSdkException("Fail to request server: " + e.getMessage(), e);
            } catch (Exception e) {
                ctx.requestFail(httpResponse);
                exception = new KnownsecSdkException("Fail to request server: " + e.getMessage(), e);
            } finally {
                ctx.incrementAttemptCount();
            }
        }
        if (exception == null) {
            try {
                return ctx.parseResponse();
            } catch (KnownsecSdkException e) {
                if (isServerError(ctx.response)) {
                    exception = e;
                } else {
                    throw e;
                }
            }
        } else {
            // 关闭最后一次请求的流
            if (ctx.response != null) {
                EntityUtils.consumeQuietly(ctx.response.getEntity());
            }
        }
        return null;
    }

    /**
     * 自定义上下文
     *
     * @param <R>
     */

    private class Context<R extends BaseResponse> {
        final BaseRequest<R> request;
        CloseableHttpResponse response;
        /**
         * 当前已尝试的请求次数
         */
        int attemptedCount = 0;
        /**
         * 是否使用请求对象中预置的域名
         */
        final boolean usePreassignedDomain;

        Context(BaseRequest<R> request) {
            this.request = request;
            usePreassignedDomain = StringUtils.isNotBlank(request.getModerationDomain());
        }

        ClassicHttpRequest createRequest() {

            HttpRequest tmpRequest = request.toHttpRequest(signer, credentials);

            HttpMethodEnum method = tmpRequest.method();
            Map<String, String> headers = tmpRequest.headers();

            ClassicRequestBuilder builder = ClassicRequestBuilder.create(method.name());
            builder.setUri(tmpRequest.assembleUrl());
            headers.forEach(builder::addHeader);

            if (method.hasContent()) {
                byte[] body = tmpRequest.body();
                if (body != null) {
                    ContentType contentType = ContentType.parse(headers.get(HttpHeaders.CONTENT_TYPE));
                    String contentEncoding = headers.get(HttpHeaders.CONTENT_ENCODING);
                    builder.setEntity(new ByteArrayEntity(body, contentType, contentEncoding));
                }
            }
            return builder.build();
        }

        void requestFail(CloseableHttpResponse response) {
            this.response = response;

            // todo  记录成功失败数据
        }

        void requestSuccess(CloseableHttpResponse response) {
            this.response = response;

            // todo  记录成功失败数据
        }

        R parseResponse() throws KnownsecSdkException {
            if (response == null) {
                throw new KnownsecSdkException("Server error. null response");
            }

            int statusCode = response.getCode();
            HttpEntity bodyEntity = response.getEntity();
            if (bodyEntity == null) {
                throw new KnownsecSdkException("Server error. null response body. code=" + statusCode);
            }

            String strBody;
            try {
                strBody = EntityUtils.toString(bodyEntity);
            } catch (Exception e) {
                throw new KnownsecSdkException("Fail to read response body. code=" + statusCode, e);
            }

            if (statusCode >= HttpStatus.SC_SERVER_ERROR) {
                throw new KnownsecSdkException(String.format("Server error. code=%s, body=%s", statusCode, strBody));
            }

            if (statusCode >= HttpStatus.SC_OK
                    && statusCode < HttpStatus.SC_REDIRECTION) {
                try {
                    // 解析response
                    return gson.fromJson(strBody, request.getResponseClass());
                } catch (Exception e) {
                    String errorMessage = String.format(
                            "Fail to parse response body. code=%s, body=%s", statusCode, strBody);
                    throw new KnownsecSdkException(errorMessage, e);
                }
            } else {
                throw new KnownsecSdkException(
                        String.format("Server response fail. code=%s, body=%s", statusCode, strBody));
            }
        }

        boolean canAttempt() {
            return attemptedCount < maxAttemptCount;
        }

        void incrementAttemptCount() {
            // 记录重试次数
            attemptedCount++;
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            // ignore
        }
    }

    @Override
    public String toString() {
        return "DefaultClient(" + "defaultModerationDomain=" + defaultModerationDomain + ", defaultProtocol=" + defaultProtocol + ", maxAttemptCount=" + maxAttemptCount + ")";
    }

    private void preheatValidation(Collection<Class<?>> preheatRequestClassesForValidation) {
        ValidationUtils.preheat(preheatRequestClassesForValidation);
    }

    private void preheatValidationByInstance(Collection<BaseRequest> preheatRequestsForValidation) {
        ValidationUtils.preheatByInstance(new ArrayList(preheatRequestsForValidation));
    }

    public static DefaultClient createDefault(Credentials credentials) {
        return new DefaultClient(ClientConfig.defaultConfig(credentials));
    }

    private static boolean isServerError(CloseableHttpResponse response) {
        return response == null || response.getCode() >= HttpStatus.SC_SERVER_ERROR || response.getEntity() == null;
    }

    public static DefaultClient createDefault(String appId, String secretKey) {
        return createDefault(new Credentials(appId, secretKey));
    }
}
