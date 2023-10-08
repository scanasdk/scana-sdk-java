package com.newkms.qixincha.moderation.image;

import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.moderation.Client;
import com.newkms.qixincha.moderation.image.v3.async.ImageAsyncModerationInput;
import com.newkms.qixincha.moderation.image.v3.async.ImageAsyncModerationOutput;
import com.newkms.qixincha.moderation.image.v3.sync.ImageSyncModerationInput;
import com.newkms.qixincha.moderation.image.v3.sync.ImageSyncModerationOutput;

/**
 * 文本审核请求客户端
 */
public class ImageModerationClient extends Client {
    public ImageModerationClient(ClientConfig clientConfig) {
        super(clientConfig);
    }

    public ImageSyncModerationOutput ImageSyncModeration(ImageSyncModerationInput input) {
        // 赋值appId secretKey
        input.setAppId(this.client.getCredentials().getAppId());
        input.setSecretKey(this.client.getCredentials().getSecretKey());
        return this.client.execute(input);
    }


    public ImageAsyncModerationOutput ImageAsyncModeration(ImageAsyncModerationInput input) {
        // 赋值appId secretKey
        input.setAppId(this.client.getCredentials().getAppId());
        input.setSecretKey(this.client.getCredentials().getSecretKey());
        return this.client.execute(input);
    }
}
