package com.newkms.qixincha.moderation.video;

import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.moderation.Client;
import com.newkms.qixincha.moderation.video.v3.VideoModerationInput;
import com.newkms.qixincha.moderation.video.v3.VideoModerationOutput;

/**
 * 文本审核请求客户端
 */
public class VideoModerationClient extends Client {
    public VideoModerationClient(ClientConfig clientConfig) {
        super(clientConfig);
    }


    public VideoModerationOutput VideoAsyncModeration(VideoModerationInput input) {
        // 赋值appId secretKey
        input.setAppId(this.client.getCredentials().getAppId());
        input.setSecretKey(this.client.getCredentials().getSecretKey());
        return this.client.execute(input);
    }
}
