package com.newkms.qixincha.moderation.text;

import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.moderation.Client;
import com.newkms.qixincha.moderation.text.v3.async.TextAsyncModerationInput;
import com.newkms.qixincha.moderation.text.v3.async.TextAsyncModerationOutput;
import com.newkms.qixincha.moderation.text.v3.sync.TextSyncModerationInput;
import com.newkms.qixincha.moderation.text.v3.sync.TextSyncModerationOutput;

/**
 * 文本审核请求客户端
 */
public class TextModerationClient extends Client {
    public TextModerationClient(ClientConfig clientConfig) {
        super(clientConfig);
    }

    public TextSyncModerationOutput TextSyncModeration(TextSyncModerationInput input) {
        // 赋值appId secretKey
        input.setAppId(this.client.getCredentials().getAppId());
        input.setSecretKey(this.client.getCredentials().getSecretKey());
        return this.client.execute(input);
    }


    public TextAsyncModerationOutput TextAsyncModeration(TextAsyncModerationInput input) {
        // 赋值appId secretKey
        input.setAppId(this.client.getCredentials().getAppId());
        input.setSecretKey(this.client.getCredentials().getSecretKey());
        return this.client.execute(input);
    }
}
