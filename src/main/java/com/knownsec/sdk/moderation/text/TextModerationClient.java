package com.knownsec.sdk.moderation.text;

import com.knownsec.sdk.client.ClientConfig;
import com.knownsec.sdk.moderation.ModerationClient;
import com.knownsec.sdk.moderation.text.v3.async.TextAsyncModerationInput;
import com.knownsec.sdk.moderation.text.v3.async.TextAsyncModerationOutput;
import com.knownsec.sdk.moderation.text.v3.sync.TextSyncModerationInput;
import com.knownsec.sdk.moderation.text.v3.sync.TextSyncModerationOutput;

/**
 * 文本审核请求客户端
 */
public class TextModerationClient extends ModerationClient {
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
