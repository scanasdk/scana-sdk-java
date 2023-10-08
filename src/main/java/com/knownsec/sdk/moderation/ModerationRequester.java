package com.knownsec.sdk.moderation;

import com.knownsec.sdk.auth.Credentials;
import com.knownsec.sdk.client.ClientConfig;
import com.knownsec.sdk.http.HttpClientConfig;
import com.knownsec.sdk.moderation.image.ImageModerationClient;
import com.knownsec.sdk.moderation.text.TextModerationClient;
import com.knownsec.sdk.utils.AssertUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 护架内容检测请求器
 */
public class ModerationRequester {
    private ClientConfig clientConfig;

    private ConcurrentHashMap<String, Object> clientMap = new ConcurrentHashMap<>();


    public ModerationRequester(String appId, String secretKey) {
        AssertUtils.notBlank(appId, "appId can not be null or empty");
        AssertUtils.notBlank(secretKey, "secretKey can not be null or empty");
        this.clientConfig = createDefaultConfig(appId, secretKey);
    }

    public String getAppId() {
        return this.clientConfig.getCredentials().getAppId();
    }

    public String getSecretKey() {
        return this.clientConfig.getCredentials().getSecretKey();
    }

    public ModerationRequester(ClientConfig clientConfig) {
        AssertUtils.notNull(clientConfig, "clientProfile can not be null");
        AssertUtils.notNull(clientConfig.getCredentials(), "credentials can not be null");
        AssertUtils.notBlank(clientConfig.getCredentials().getAppId(), "appId can not be null or empty");
        AssertUtils.notBlank(clientConfig.getCredentials().getSecretKey(), "secretKey can not be null or empty");
        this.clientConfig = clientConfig;
    }

    public static ClientConfig createDefaultConfig(String appId, String secretKey) {
        ClientConfig clientConfig = ClientConfig.defaultConfig(new Credentials(appId, secretKey));
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        // todo  v3 默认不要签名，如果需要签名这里改
        clientConfig.setSigner(null);
        // 设置 httpClientConfig
        clientConfig.setHttpClientConfig(httpClientConfig);
        httpClientConfig.setMaxConnectionCountPerRoute(100);
        return clientConfig;
    }

    public TextModerationClient getTextCheckClient() {
        return createIfAbsent(TextModerationClient.class);
    }

    public ImageModerationClient getImageCheckClient() {
        return createIfAbsent(ImageModerationClient.class);
    }

    private <T extends ModerationClient> T createIfAbsent(Class<T> clazz) {
        String name = clazz.getName();
        Object client = clientMap.get(name);
        if (client != null) {
            return (T) client;
        }
        return (T) clientMap.computeIfAbsent(name, k -> {
            try {
                return clazz.getDeclaredConstructor(ClientConfig.class).newInstance(this.clientConfig);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
