package com.knownsec.sdk.moderation.image;

import com.knownsec.sdk.moderation.BaseClient;
import com.knownsec.sdk.moderation.ModerationRequester;
import com.knownsec.sdk.moderation.image.v3.async.ImageAsyncModerationInput;
import com.knownsec.sdk.moderation.image.v3.async.ImageAsyncModerationOutput;
import com.knownsec.sdk.moderation.image.v3.sync.ImageSyncModerationInput;
import com.knownsec.sdk.moderation.image.v3.sync.ImageSyncModerationOutput;

public class ImageClient extends BaseClient {
    public ImageClient(ModerationRequester requester) {
        super(requester);
    }

    /**
     * 图片同步请求
     *
     * @param input TextSyncModerationInput
     * @return TextSyncModerationOutput
     */

    public ImageSyncModerationOutput ImageSyncModeration(ImageSyncModerationInput input) {
        return this.requester.getImageCheckClient().ImageSyncModeration(input);
    }


    /**
     * 图片异步审核请求
     *
     * @param Input TextAsyncModerationInput
     * @return TextAsyncModerationOutput
     */
    public ImageAsyncModerationOutput ImageAsyncModeration(ImageAsyncModerationInput Input) {
        return this.requester.getImageCheckClient().ImageAsyncModeration(Input);
    }
}
