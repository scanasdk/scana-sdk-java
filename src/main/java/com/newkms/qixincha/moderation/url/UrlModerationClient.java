package com.newkms.qixincha.moderation.url;

import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.moderation.Client;
import com.newkms.qixincha.moderation.url.v3.UrlModerationInput;
import com.newkms.qixincha.moderation.url.v3.UrlModerationOutput;

/*
 * url 审核请求客户端
*/
public class UrlModerationClient extends Client {
    public UrlModerationClient(ClientConfig clientConfig) {
        super(clientConfig);
    }

    public UrlModerationOutput UrlAsyncModeration(UrlModerationInput input) {
        // 赋值appId secretKey
        input.setAppId(this.client.getCredentials().getAppId());
        input.setSecretKey(this.client.getCredentials().getSecretKey());
        return this.client.execute(input);
    }

}
