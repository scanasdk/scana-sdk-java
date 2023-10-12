package com.newkms.qixincha.moderation;

import com.newkms.qixincha.auth.Credentials;
import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.http.HttpClientConfig;
import com.newkms.qixincha.moderation.audio.AudioModerationClient;
import com.newkms.qixincha.moderation.doc.DocModerationClient;
import com.newkms.qixincha.moderation.image.ImageModerationClient;
import com.newkms.qixincha.moderation.text.TextModerationClient;
import com.newkms.qixincha.moderation.video.VideoModerationClient;
import com.newkms.qixincha.utils.AssertUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 护架内容检测请求器
 */
public class ModerationRequester {
    private ClientConfig clientConfig;

    private ConcurrentHashMap<String, Object> clientMap = new ConcurrentHashMap<>();


    /**
     * 通过appId 和 secretKey创建一个请求器
     *
     * @param appId
     * @param secretKey
     */
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

    /**
     * 自定义配置创建一个请求器
     *
     * @param clientConfig
     */
    public ModerationRequester(ClientConfig clientConfig) {
        AssertUtils.notNull(clientConfig, "clientProfile can not be null");
        AssertUtils.notNull(clientConfig.getCredentials(), "credentials can not be null");
        AssertUtils.notBlank(clientConfig.getCredentials().getAppId(), "appId can not be null or empty");
        AssertUtils.notBlank(clientConfig.getCredentials().getSecretKey(), "secretKey can not be null or empty");
        this.clientConfig = clientConfig;
    }

    /**
     * 创建默认配置
     *
     * @param appId
     * @param secretKey
     * @return
     */
    public static ClientConfig createDefaultConfig(String appId, String secretKey) {
        // 创建一个默认的客户端配置
        ClientConfig clientConfig = ClientConfig.defaultConfig(new Credentials(appId, secretKey));
        // 创建一个http 客户端配置
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        // todo  v3 默认不要签名，如果需要签名这里改
        clientConfig.setSigner(null);
        // 设置 httpClientConfig
        httpClientConfig.setMaxConnectionCountPerRoute(100);
        clientConfig.setHttpClientConfig(httpClientConfig);
        return clientConfig;
    }

    /**
     * 获取默认配置
     *
     * @return
     */
    public ClientConfig getClientConfig() {
        return this.clientConfig;
    }


    public TextModerationClient getTextCheckClient() {
        return createIfAbsent(TextModerationClient.class);
    }

    public AudioModerationClient getAudioCheckClient() {
        return createIfAbsent(AudioModerationClient.class);
    }

    public DocModerationClient getDocCheckClient() {
        return createIfAbsent(DocModerationClient.class);
    }

    public VideoModerationClient getVideoCheckClient() {
        return createIfAbsent(VideoModerationClient.class);
    }

    public ImageModerationClient getImageCheckClient() {
        return createIfAbsent(ImageModerationClient.class);
    }

    /**
     * 获取客户端
     *
     * @param clazz
     * @param <T>
     * @return
     */
    private <T extends Client> T createIfAbsent(Class<T> clazz) {
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
