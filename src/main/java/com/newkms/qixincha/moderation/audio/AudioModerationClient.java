package com.newkms.qixincha.moderation.audio;

import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.moderation.Client;
import com.newkms.qixincha.moderation.audio.v3.AudioModerationInput;
import com.newkms.qixincha.moderation.audio.v3.AudioModerationOutput;

/**
 * 文本审核请求客户端
 */
public class AudioModerationClient extends Client {
    public AudioModerationClient(ClientConfig clientConfig) {
        super(clientConfig);
    }


    public AudioModerationOutput AudioAsyncModeration(AudioModerationInput input) {
        // 赋值appId secretKey
        input.setAppId(this.client.getCredentials().getAppId());
        input.setSecretKey(this.client.getCredentials().getSecretKey());
        return this.client.execute(input);
    }
}
